package com.devcation.sns.data;


import com.devcation.sns.model.FileEntity;
import com.devcation.sns.model.SnsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  // 인자가 없는
@AllArgsConstructor // 인자가 있는
@Data // getter setter
public class FileDTO {

    private Integer id;
    private Integer snsid;
    private String userid;
    private String filename;


    public FileDTO (final FileEntity fileEntity) {
        this.id = fileEntity.getId();
        this.snsid = fileEntity.getSnsid();
        this.userid = fileEntity.getUserid();
        this.filename = fileEntity.getFilename();

    }

    //request
    public static FileEntity toFileEntity(final FileDTO dto) {
        return FileEntity.builder()
                .id(dto.getId())
                .snsid(dto.getSnsid())
                .userid(dto.getUserid())
                .filename(dto.getFilename())
                .build();
    }
}
