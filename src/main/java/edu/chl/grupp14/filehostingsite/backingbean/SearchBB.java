
package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("searchBB")
@RequestScoped
public class SearchBB implements Serializable {
    private HostedFileDatabase hostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    private List<HostedFile> searchResults = new ArrayList<>();
    private String keyword;
    
    public SearchBB() {
        
    }
    public List<HostedFile> getSearchResults(){
        searchResults.clear();
        
        for (HostedFile f : hostedFileDB.getFilesContaining(keyword)) {
            searchResults.add(f);
        }  
        return searchResults;
    }
    
    public void setSearchResults(List<HostedFile> filelist) {
        searchResults = filelist;
    }
    
    public String search() {
        getSearchResults();
        return "/searchView";
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getKeyword() {
        return keyword;
    }
}
