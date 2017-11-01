package org.usfirst.frc.team4946.robot.subsystems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4946.robot.RobotConstants;
import org.zeromq.ZMQ;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	public static final double H_FOV = 61.372724804;

	class ContourAreaComparator implements Comparator<MatOfPoint> {

		@Override
		public int compare(MatOfPoint o1, MatOfPoint o2) {
			return new Double(Imgproc.contourArea(o1)).compareTo(Imgproc
					.contourArea(o2));
		}

	}

	// Onboard Processing
	private UsbCamera gearCamera;
	// private UsbCamera ropeCamera;
	public double m_gearAngle = 0;
	public boolean m_hasFreshGearAngle = false;
	private boolean m_shouldProcessGear = false;

	// Offboard Processing
	private ZMQ.Context m_context;
	private ZMQ.Socket m_subscriber;
	private long m_lastJetsonTime;
	private long m_jetsonBindTime;
	private long m_jetsonLastValidTime;
	private double m_shooterDistInches = -1;
	private double m_shooterRPM = -1;
	private double m_shooterAngle = 0;

	public Vision() {
		m_context = ZMQ.context(1);
		m_subscriber = m_context.socket(ZMQ.SUB);
		m_subscriber.setRcvHWM(5);
	}

	public void initDefaultCommand() {
	}

	/**
	 * 
	 * @return {@code true} if the Jetson has issued a fresh value in the last
	 *         0.2 seconds, otherwise {@code false}
	 */
	public boolean jetsonHasFreshValue() {

		// Seconds * conversion
		double nanoseconds = 0.2 * 1e+9;

		return System.nanoTime() - m_lastJetsonTime < nanoseconds;
	}

	/**
	 * 
	 * @return {@code true} if the Jetson has issued a fresh value in the last
	 *         0.2 seconds, otherwise {@code false}
	 */
	public boolean jetsonHasFreshValidValue() {

		// Seconds * conversion
		double nanoseconds = 0.2 * 1e+9;

		return System.nanoTime() - m_jetsonLastValidTime < nanoseconds;
	}

	// /**
	// * Engage or Disengage the gear vision processing
	// *
	// * @param shouldProcess
	// * whether or not to process
	// */
	// public void setGearIsActive(boolean shouldProcess) {
	// m_shouldProcessGear = shouldProcess;
	// if (m_shouldProcessGear)
	// gearCamera.setExposureManual(0);
	// else
	// gearCamera.setExposureManual(60);
	// }

	/**
	 * Try to fetch a message from the Jetson via ZMQ. This function will block
	 * until a message is found.
	 */
	private void fetch() {
		try {
			String reply = m_subscriber.recvStr(ZMQ.NOBLOCK);

			if (reply != null) {
				m_lastJetsonTime = System.nanoTime();

				/*
				 * if (reply.trim().isEmpty() ||
				 * reply.trim().equalsIgnoreCase("Error")) { System.out
				 * .println(
				 * "Connection established, waiting for valid data..."); try {
				 * Thread.sleep(100); } catch (InterruptedException e1) { } }
				 * else
				 */if (reply.trim().equalsIgnoreCase("Boiler Not Found"))
					System.out.println("Data valid. Boilder not found.");
				else {
					// System.out.println("aa");
					Map<String, Double> data = parseData(reply);
					processData(data);
				}
			} else {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse the message from the Jetson into a {@link HashMap} of keys and
	 * values
	 * 
	 * @param recieve
	 *            the message from the Jetson
	 * @return a {@code HashMap} created from the key/value pairs
	 */
	private HashMap<String, Double> parseData(String recieve) {

		HashMap<String, Double> data = new HashMap<String, Double>();

		Scanner scanner = new Scanner(recieve);
		scanner.useDelimiter(";");

		boolean didProcess = false;
		while (scanner.hasNext()) {
			didProcess = true;
			String line = scanner.next();
			if (!line.startsWith("_"))
				continue;
			line = line.substring(1);
			String[] columns = line.split("=");
			// System.out.println(columns[0] + "|" + columns[1]);
			data.put(columns[0], Double.parseDouble(columns[1].trim()));
			m_jetsonLastValidTime = System.nanoTime();
		}
		// if (didProcess)
		// System.out.println();

		scanner.close();
		return data;
	}

	/**
	 * Pull the useful values out of the {@link Map} of key/value pairs
	 * 
	 * @param data
	 *            the {@code Map} to process
	 */
	private void processData(Map<String, Double> data) {
		if (data.containsKey("distInches"))
			m_shooterDistInches = data.get("distInches");
		if (data.containsKey("RPM"))
			m_shooterRPM = data.get("RPM");
		if (data.containsKey("shootAngle"))
			m_shooterAngle = (data.get("shootAngle") + RobotConstants.cameraOffset);

	}

	Thread visionThread = new Thread(() -> {
		// Get the UsbCamera from CameraServer
			gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
			// ropeCamera = CameraServer.getInstance().startAutomaticCapture(1);

			// Set the resolution
			gearCamera.setResolution(640, 480);
			// ropeCamera.setResolution(320, 240);
			gearCamera.setExposureManual(60);
			// ropeCamera.setExposureAuto();

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo(gearCamera);
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Gear",
					640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();
			Mat binary = new Mat();

			// Imgproc.cvtColor(mat, mat, Imgproc.);
			// This cannot be 'true'. The program will never exit if it is.
			// This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {

				try {

					// Tell the CvSink to grab a frame from the camera and
					// put
					// it
					// in the source mat. If there is an error notify the
					// output.
					if (cvSink.grabFrame(mat) == 0) {
						// Send the output the error.
						outputStream.notifyError(cvSink.getError());
						// skip the rest of the current iteration
						continue;
					}

					// 0 = around x, 1 = around y, -1 = both
					Core.flip(mat, mat, -1);

					if (!m_shouldProcessGear) {
						outputStream.putFrame(mat);
						continue;
					}

					System.out.println("VISN");

					// Mast off the top of the frame
					Imgproc.rectangle(mat, new Point(0, 0),
							new Point(mat.cols(), mat.cols() / 6), new Scalar(
									0, 0, 0), -1);

					// Convert to HSV and perform a range
			Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);
			Core.inRange(mat, new Scalar(60, 100, 100), new Scalar(100, 256,
					256), binary);

			// Find the contours from the binary image
			List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			Imgproc.findContours(binary, contours, binary,
					Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
			contours.sort(new ContourAreaComparator());

			for (int i = 0; i < contours.size(); i++) {
				Imgproc.drawContours(mat, contours, i, new Scalar(255, 0, 0));
				Imgproc.fillConvexPoly(mat, contours.get(i), new Scalar(255, 0,
						0));
			}

			System.out.println("Countours: " + contours.size());

			if (contours.size() >= 2) {

				// System.out.println("a");
				List<Point> targetPts = new ArrayList<Point>();
				// System.out.println("a1");

				targetPts.addAll(contours.get(0).toList());
				// System.out.println("b");

				targetPts.addAll(contours.get(1).toList());
				// System.out.println("c");

				MatOfPoint targetMat = new MatOfPoint();
				// System.out.println("d");

				targetMat.fromList(targetPts);
				// System.out.println("e");
				Rect rect = Imgproc.boundingRect(targetMat);

				// System.out.println("f");
				Imgproc.rectangle(mat, rect.tl(), rect.br(), new Scalar(0, 0,
						255), 2, 8, 0);
				// System.out.println("g");

				Point center = new Point(rect.x + (rect.width / 2), rect.y
						+ (rect.height) / 2);

				// System.out.println("h");
				double percent = center.x / (double) mat.cols();
				m_hasFreshGearAngle = true;

				double height = rect.height;
				double range = (height * -0.06) + 20.833;

				m_gearAngle = ((percent * 2) - 1) * range;

				Imgproc.putText(mat, "111", new Point(50, 50),
						Core.FONT_HERSHEY_PLAIN, 1.0, new Scalar(255, 0, 0));

				System.out.println("%: " + percent + "   deg:" + m_gearAngle
						+ "   h:" + rect.height);

			}

			// Give the output stream a new image to display
			outputStream.putFrame(mat);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	);

	public void startVisionThread() {
		visionThread.setDaemon(true);
		visionThread.start();
	}

	public void startSimpleVision() {
		gearCamera = CameraServer.getInstance().startAutomaticCapture();
//		gearCamera.setBrightness(50);
		gearCamera.setResolution(120, 120);
		
		
		for(VideoMode mode : gearCamera.enumerateVideoModes())
			if(mode.fps == 30)
				gearCamera.setVideoMode(mode);
		
		//640 360
		//320 180
		//160 90

	}

	Thread zmqThread = new Thread(
			() -> {
				boolean didBind = false;

				// This cannot be 'true'. The program will never exit if it is.
				// This
				// lets the robot stop this thread when restarting robot code or
				// deploying.
				while (!Thread.interrupted()) {

					if (!didBind) {
						try {
							// m_subscriber
							// .connect("tcp://tegra-ubuntu.local:5800");
							m_subscriber.connect("tcp://10.49.46.10:5800");
							m_subscriber.subscribe(new byte[] {});
							didBind = true;
							System.out.println("Found Jetson!");
							m_jetsonBindTime = System.nanoTime();
						} catch (IllegalArgumentException e) {
							System.out
									.println("Can't find Jetson... Trying again in 3 sec");

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
							}

						}
					} else {

						try {
							fetch();

						} catch (Exception e) {
							e.printStackTrace();
						}

						if ((System.nanoTime() - m_lastJetsonTime > 2.0 * 1e+9)
								&& (System.nanoTime() - m_jetsonBindTime > 2.0 * 1e+9))
							didBind = false;
					}
				}
			});

	public void startZMQThread() {
		zmqThread.setDaemon(true);
		zmqThread.start();
	}

	public double getShooterRPM() {
		return m_shooterRPM;
	}

	public double getShooterDistInches() {
		return m_shooterDistInches;
	}

	public double getShooterAngle() {
		return m_shooterAngle;
	}

	public boolean jetsonIsConnected() {
		return jetsonHasFreshValue();
	}

}
