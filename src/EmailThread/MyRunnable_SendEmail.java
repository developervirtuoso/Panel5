package EmailThread;

import api.daoImpl.VFMail;

public class MyRunnable_SendEmail implements Runnable{

	String to_Email;String Cc_Email;String txt_msg;String requested_date;String sub;

    public MyRunnable_SendEmail(String toemail,String cc,String sub,String txt,String datee) {
        this.to_Email = toemail;
  
        this.Cc_Email=cc;
        this.txt_msg=txt;
        this.requested_date=datee;
        this.sub=sub;
        
       
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new VFMail(to_Email,Cc_Email,sub,txt_msg,requested_date);
		
	}

}
