package book.cap.c2175.p730;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreenhouseScheduler {
	private volatile boolean light = false;
	private volatile boolean water = false;
	private String thermostat = "Day";
	public synchronized String getThermostat() {
		return thermostat;
	}
	public synchronized void setThermostat(String value) {
		thermostat = value;
	}
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
	/**
	 * ���ü��ʱ��ִ�У�ִֻ��һ��
	 * @param event
	 * @param delay
	 */
	public void schedule(Runnable event, long delay) {
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}
	/**
	 * ���ü������һ������initialDelay�����֮���ظ�����period���
	 * @param event
	 * @param initialDelay
	 * @param period
	 */
	public void repeat(Runnable event, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
	}
	/**
	 * ����
	 * @author zhb
	 *
	 */
	class LightOn implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Turning on lights");
			light = true;
		}
		
	}
	/**
	 * �ص�
	 * @author zhb
	 *
	 */
	class LightOff implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Turnning off lights");
			light = false;
			
		}
		
	}
	/**
	 * ��ˮ
	 * @author zhb
	 *
	 */
	class WaterOn implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Turning greenhouse water on");
			water = true;
		}
	}
	/**
	 * ��ˮ
	 * @author zhb
	 *
	 */
	class WaterOff implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Truning greenhouse water off");
			water = false;
		}
	}
	/**
	 * ���µ�ҹ��
	 * @author zhb
	 *
	 */
	class ThermostatNight implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thermostat to night setting");
			setThermostat("Night");
		}
	}
	/**
	 * ���µư���
	 * @author zhb
	 *
	 */
	class ThermostatDay implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thermostat to day setting");
			setThermostat("Day");
		}
	}
	/**
	 * ����
	 * @author zhb
	 *
	 */
	class Bell implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Bing!");
		}
		
	}
	/**
	 * ֹͣ����
	 * @author zhb
	 *
	 */
	class Terminate implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Terminating");
			scheduler.shutdownNow();
			
			new Thread() {
				public void run() {
					for(DataPoint d : data) {
						System.out.println(d);
					}
				}
			};
		}
	}
	
	static class DataPoint {
		final Calendar time;//ʱ��
		final float temperature;//�¶�
		final float humidity;//ʪ��
		public DataPoint(Calendar time, float temperature, float humidity) {
			this.time = time;
			this.temperature = temperature;
			this.humidity = humidity;
		}
		public String toString() {
			return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
		}
	}
	private Calendar lastTime = Calendar.getInstance();
	{
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}
	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection = +1;
	private Random rand = new Random(47);
	List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
	
	
	class CollectData implements Runnable {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Collecting data");
			synchronized(GreenhouseScheduler.this) {
				lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
				
				if(rand.nextInt(5) == 4) {
					tempDirection = -tempDirection;
				}
				
				lastTemp += tempDirection * (1.0f + rand.nextFloat());
				
				if(rand.nextInt(5) == 4) {
					humidityDirection = -humidityDirection;
				}
				
				lastHumidity += humidityDirection * rand.nextFloat();
				
				data.add(new DataPoint((Calendar)lastTime.clone(), lastTemp, lastHumidity));
			}
		}
		
	}
	public static void main(String[] args) {
		GreenhouseScheduler gh = new GreenhouseScheduler();
		gh.schedule(gh.new Terminate(), 5000);
		
		gh.repeat(gh.new Bell(), 0, 1000);
		gh.repeat(gh.new ThermostatNight(), 0, 2000);
		gh.repeat(gh.new LightOn(), 0, 200);
		gh.repeat(gh.new LightOff(), 0, 400);
		gh.repeat(gh.new WaterOn(), 0, 600);
		gh.repeat(gh.new WaterOff(), 0, 800);
		gh.repeat(gh.new ThermostatDay(), 0, 1400);
		gh.repeat(gh.new CollectData(), 500, 500);
	}
	
}
