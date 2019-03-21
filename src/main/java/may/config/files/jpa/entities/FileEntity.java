package may.config.files.jpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity extends PanacheEntity implements java.io.Serializable {

    @Id
    @Column(name = "filename")
    @Access(AccessType.PROPERTY)
    private String filename;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file_bytes")
    private byte[] fileBytes;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] file) {
        this.fileBytes = file;
    }
}
