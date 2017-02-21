package org.usfirst.frc.team4946.robot.subsystems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.zeromq.ZMQ;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	ZMQ.Context context;
	ZMQ.Socket subscriber;
	ZMQ.Socket publisher;


	public double m_distInches = -1;
	double m_rpm = -1;
	double m_horizAngle = 0;

	boolean m_camIsGear = true;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Vision() {
		context = ZMQ.context(1);
		subscriber = context.socket(ZMQ.SUB);
		subscriber.connect("tcp://*5800");
		
		publisher = context.socket(ZMQ.PUB);
		publisher.connect("tcp://*5801");
		publisher.setSndHWM(1);
	}

	public void send() {
//		String send = "camIsGear=" + (m_camIsGear ? "true" : "false");
//		publisher.send(send.getBytes(), ZMQ.NOBLOCK);

	}

	public void fetch() {
		byte[] reply = subscriber.recv();
		Map<String, Double> data = parseData(new String(reply));
		processData(data);
	}

	@SuppressWarnings("unused")
	private Map<String, Double> parseData(String recieve) {

		Map<String, Double> data = new HashMap<String, Double>();

		Scanner scanner = new Scanner(recieve);
		scanner.useDelimiter(";");

		while (scanner.hasNext()) {
			String line = scanner.next();
			String[] columns = line.split("=");
			data.put(columns[0], Double.parseDouble(columns[1]));
		}

		scanner.close();
		return data;
	}

	@SuppressWarnings("unused")
	private void processData(Map<String, Double> data) {
		if (data.containsKey("distInches"))
			m_distInches = data.get("distInches");
		if (data.containsKey("RPM"))
			m_rpm = data.get("RPM");
		if (data.containsKey("angle"))
			m_horizAngle = data.get("angle");
	}
}
