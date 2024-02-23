package anime_list.model.vo;

import lombok.Data;

@Data
public class Comment {
    private String commnetPk;
    private String userPk;
    private String aniPk;
    private String content;
}
