package se.patrikbergman.java.ee.examples.interceptor.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.io.Serializable;

@Stateless(mappedName = "BandBean")
@Remote(BandService.class)
public class BandBean implements Serializable, BandService {
    public static final long serialVersionUID = 1L;


    @Interceptors({Performance.class, Log.class})
    public String hello(String name) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
		return "Hello " + name + "! ";
    }

	@Override
	public void play() {

	}

	@Override
	public int getRockFactor() {
		return 0;
	}

}
