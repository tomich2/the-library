package cz.fi.muni.pa165.dto;

import java.util.Objects;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Data Transfer object for creating a Book entity
 * 
 * @author Martin Palenik
 */
public class CreateBookDTO {
    @NotBlank
    @Size(max = 150)
    private String title;
    
    @NotBlank
    @Size(max = 100)
    private String author;

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 8;
        hash = 52 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final CreateBookDTO other = (CreateBookDTO) obj;
        
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        
        return true;
    }
}