package anime_list.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private String commentPk;
    private String userPk;
    private String aniPk;
    private String content;
    private Date commentDate;
    private float initGrade;

}
