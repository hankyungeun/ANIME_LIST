package anime_list.model.vo;

import lombok.Data;

@Data
public class User {
    private String userPk;
    private String userId;
    private String passwd;
    private String name;
}
