package br.com.autoservice.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
public class HibernateUtil {
 
    public static final SessionFactory session = buildSession();
 
    private static SessionFactory buildSession() {
 
    try{
        // Configuration cfg = new Configuration();
          //             cfg.configure("hibernate.cfg.xml");
    	return new AnnotationConfiguration().configure().buildSessionFactory();
 
       // return cfg.buildSessionFactory();
 
       }catch(Throwable b){
 
            System.out.println("Não deu hibernate util \n" + b);
            throw new ExceptionInInitializerError();
       }
   }
 
   public static SessionFactory getSessionFactory(){
       return session;
   }
}