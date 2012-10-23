/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author Gustav
 */
@Named("searchBB")
@RequestScoped
public class SearchBB implements Serializable {
    private HostedFileDatabase hostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    private List<HostedFile> searchResults = new ArrayList<>();
    private String keyword;
    
    public void SearchBB() {
        
    }
    
    public List<HostedFile> getSearchResults(){
        searchResults = new ArrayList<>();
        searchResults.addAll(hostedFileDB.getFilesContaining(keyword));
        return searchResults;
    }
    
    public void setSearchResults(List<HostedFile> filelist) {
        searchResults = filelist;
    }
    
    public void search() {
        searchResults = new ArrayList<>();
        searchResults.addAll(hostedFileDB.getFilesContaining(keyword));
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getKeyword() {
        return keyword;
    }
}
