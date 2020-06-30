package com.test;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import api.daoImpl.Smpp_DaoImpl;
public class StrongAES {
	public static void main(String[] args) {
		try 
        {
           Smpp_DaoImpl AES=new Smpp_DaoImpl();
           String originalString = "C:\\Users\\Dell\\eclipse-vns\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Panel5\\UploadedImages\\Excel\\count.xlsximageC:\\Users\\Dell\\eclipse-vns\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Panel5\\UploadedImages\\Excel\\count.xlsx";
           String encryptedString = AES.encrypt(originalString) ;
           String decryptedString = AES.decrypt("ILv7v85dd9ABPnHFIfIT7rxqEcb6xsVpA3d16XCR4BJMhrx2QX+bEdDofgvbMHGzeDJaLMY41sP0Sqaa6SR0k+fJ4XrAl7SraePhb9fgw7OFupsO1eY/LPJMcukAO9CzadyZ/DDg+ab4hGs3/xF3WGjzjbIby+fdBV/qGCMsW0Q=") ;
           									   //ILv7v85dd9ABPnHFIfIT7rxqEcb6xsVpA3d16XCR4BJMhrx2QX bEdDofgvbMHGzeDJaLMY41sP0Sqaa6SR0k fJ4XrAl7SraePhb9fgw7OFupsO1eY/LPJMcukAO9CzadyZ/DDg ab4hGs3/xF3WGjzjbIby fdBV/qGCMsW0Q=
           System.out.println(originalString);
           System.out.println(encryptedString);
           System.out.println(decryptedString);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
	}
}
