/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chs.edu.grupp14.filehostingsite.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import chs.edu.grupp14.filehostingsite.core.HostedFile;
import chs.edu.grupp14.filehostingsite.core.HostedFileDatabase;

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
        searchResults.clear();
        
        for (HostedFile f : hostedFileDB.getFilesContaining(keyword)) {
            if (f.isPublic()) {
                searchResults.add(f);
            }
        }
        
        return searchResults;
    }
    
    public void setSearchResults(List<HostedFile> filelist) {
        searchResults = filelist;
    }
    
    public String search() {
        getSearchResults();
        return "searchView";
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getKeyword() {
        return keyword;
    }
}