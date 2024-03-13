package anime_list.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {
    private String commentPk;
    private String userPk;
    private String aniPk;
    private String content;
    private Date commentDate;
    private float initGrade;

}
