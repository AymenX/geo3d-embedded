package org.avm.elementary.command.commands;

import java.util.BitSet;
import java.util.Iterator;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.avm.business.protocol.phoebus.Anomalie;
import org.avm.business.protocol.phoebus.Message;
import org.avm.elementary.alarm.Alarm;
import org.avm.elementary.alarm.AlarmService;
import org.avm.elementary.command.MessengerContext;
import org.avm.elementary.command.impl.CommandFactory;
import org.osgi.service.component.ComponentContext;

public class AlarmCommand implements org.apache.commons.chain.Command {

	/** Fabrique de classe */
	public static class DefaultCommandFactory extends CommandFactory {

		protected Command createCommand() {

			return new AlarmCommand();
		}
	}

	private MessengerContext _context;

	private boolean _wasAlarm = false;

	/** Referencement de la fabrique de classe */
	static {
		final CommandFactory factory = new DefaultCommandFactory();
		CommandFactory.factories.put(AlarmCommand.class.getName(), factory);
	}

	public boolean execute(final Context context) throws Exception {

		this._context = (MessengerContext) context;
		if (this._context == null) {
			return true;
		}
		final Object msg = this._context.getMessage();
		if (msg == null) {
			return true;
		}
		if (msg instanceof Message) {
			final Message message = (Message) msg;
			final ComponentContext cc = this._context.getComponentContext();
			final AlarmService alarm = (AlarmService) cc.locateService("alarm");
			if (alarm != null) {
				boolean currentState = alarm.isAlarm();
				if (_wasAlarm != currentState || currentState) {
					System.err.println("[AlarmCommand] _wasAlarm="+_wasAlarm+", currentState="+currentState+" ************");
					_wasAlarm = currentState;
					message.getEntete().getChamps().setAnomalie(1);
					final BitSet alarms = new BitSet();
					final Iterator iter = alarm.getList().iterator();
					while (iter.hasNext()) {
						final Alarm a = (Alarm) iter.next();
						if (a.isStatus()) {
							alarms.set(a.getIndex().intValue());
						} else {
							alarms.clear(a.getIndex().intValue());
						}
					}
					if (message.getEntete().getAnomalie() == null) {
						message.getEntete().setAnomalie(new Anomalie());
					}
					final Anomalie anomalie = message.getEntete().getAnomalie();
					anomalie.setAlarme_0(alarms.get(0) ? 1 : 0);
					anomalie.setAlarme_1(alarms.get(1) ? 1 : 0);
					anomalie.setAlarme_2(alarms.get(2) ? 1 : 0);
					anomalie.setAlarme_3(alarms.get(3) ? 1 : 0);
					anomalie.setAlarme_4(alarms.get(4) ? 1 : 0);
					anomalie.setAlarme_5(alarms.get(5) ? 1 : 0);
					anomalie.setAlarme_6(alarms.get(6) ? 1 : 0);
					anomalie.setAlarme_7(alarms.get(7) ? 1 : 0);
					anomalie.setAlarme_8(alarms.get(8) ? 1 : 0);
					anomalie.setAlarme_9(alarms.get(9) ? 1 : 0);
					anomalie.setAlarme_10(alarms.get(10) ? 1 : 0);
					anomalie.setAlarme_11(alarms.get(11) ? 1 : 0);
					anomalie.setAlarme_12(alarms.get(12) ? 1 : 0);
					anomalie.setAlarme_13(alarms.get(13) ? 1 : 0);
					anomalie.setAlarme_14(alarms.get(14) ? 1 : 0);
					anomalie.setAlarme_15(alarms.get(15) ? 1 : 0);
					anomalie.setAlarme_16(alarms.get(16) ? 1 : 0);
					anomalie.setAlarme_17(alarms.get(17) ? 1 : 0);
					anomalie.setAlarme_18(alarms.get(18) ? 1 : 0);
					anomalie.setAlarme_19(alarms.get(19) ? 1 : 0);
					anomalie.setAlarme_20(alarms.get(20) ? 1 : 0);
					anomalie.setAlarme_21(alarms.get(21) ? 1 : 0);
					anomalie.setAlarme_22(alarms.get(22) ? 1 : 0);
					anomalie.setAlarme_23(alarms.get(23) ? 1 : 0);
					anomalie.setAlarme_24(alarms.get(24) ? 1 : 0);
					anomalie.setAlarme_25(alarms.get(25) ? 1 : 0);
					anomalie.setAlarme_26(alarms.get(26) ? 1 : 0);
					anomalie.setAlarme_27(alarms.get(27) ? 1 : 0);
					anomalie.setAlarme_28(alarms.get(28) ? 1 : 0);
					anomalie.setAlarme_29(alarms.get(29) ? 1 : 0);
					anomalie.setAlarme_30(alarms.get(30) ? 1 : 0);
					anomalie.setAlarme_31(alarms.get(31) ? 1 : 0);
				}
			} else {
				message.getEntete().getChamps().setAnomalie(0);
				message.getEntete().setAnomalie(null);
			}
		}
		return false;
	}
}
