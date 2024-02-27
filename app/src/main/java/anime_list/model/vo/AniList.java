package anime_list.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AniList {
    private String aniPk;
    private String title;
    private String genre;
    private String detail;
    private float grade;
    private Date startDate;
    private String imgUrl;
    private String videoUrl;
}
