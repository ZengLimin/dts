package cn.com.chinatelecom.wsdl.ctcc.sms.send.v2_1._interface;

import java.util.List;

import cn.com.chinatelecom.schema.ctcc.common.v2_1.ChargingInformation;
import cn.com.chinatelecom.schema.ctcc.common.v2_1.SimpleReference;
import cn.com.chinatelecom.schema.ctcc.sms.v2_1.DeliveryInformation;
import cn.com.chinatelecom.schema.ctcc.sms.v2_1.SmsFormat;
import cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.PolicyException;
import cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.ServiceException;

public class SendSmsImpl implements SendSms {

	@Override
	public String sendSms(List<String> addresses, String senderName,
			ChargingInformation charging, String message,
			SimpleReference receiptRequest) throws ServiceException,
			PolicyException {
		cn.com.chinatelecom.schema.ctcc.common.v2_1.ServiceException s = new cn.com.chinatelecom.schema.ctcc.common.v2_1.ServiceException();
		s.setMessageId("aaa");
		s.setText("bbbb");
		throw new ServiceException("aa", s);
	}

	@Override
	public List<DeliveryInformation> getSmsDeliveryStatus(
			String requestIdentifier) throws ServiceException, PolicyException {
		return null;
	}

	@Override
	public String sendSmsLogo(List<String> addresses, String senderName,
			ChargingInformation charging, byte[] image, SmsFormat smsFormat,
			SimpleReference receiptRequest) throws ServiceException,
			PolicyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendSmsRingtone(List<String> addresses, String senderName,
			ChargingInformation charging, String ringtone, SmsFormat smsFormat,
			SimpleReference receiptRequest) throws ServiceException,
			PolicyException {
		// TODO Auto-generated method stub
		return null;
	}

}
