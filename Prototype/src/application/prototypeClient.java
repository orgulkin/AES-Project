package application;

import ocsf.client.AbstractClient;

public class prototypeClient extends AbstractClient{
	
	QuestionsM questions = new QuestionsM();
	
	public static boolean gotmsg = false;

	public prototypeClient(String host, int port) {
		super(host, port);
	}
	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		if(msg.getClass().equals(QuestionsM.class)) {
			questions = (QuestionsM)msg;
			gotmsg=true;
		} else if (msg.getClass().equals(String.class)) {
			String m = (String) msg;
			if (m.equals("Done")) {
				gotmsg=true;
			}
		}
	}

}
