package se.baxemyr.filehostingsite.core;

/**
 *
 * @author Gustav
 */
public class AbstractHostedFileDatabase extends AbstractDAO<AbstractHostedFile, Long> {

    private AbstractHostedFileDatabase(String puName) {
        super(AbstractHostedFile.class, puName);
    }
    
    public static AbstractHostedFileDatabase newInstance(String puName){
        return new AbstractHostedFileDatabase(puName);
    }
}
