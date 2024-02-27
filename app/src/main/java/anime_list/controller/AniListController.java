package anime_list.controller;
import java.util.List;

import anime_list.model.vo.AniList;
import anime_list.service.AniListService;
public class AniListController {

    public List<AniList> getLatestAniList() {

        List<AniList> aniList = new AniListService().selectAllList();

        if (aniList.isEmpty()) {
            System.out.println("데이터없음!!!");
        } else {
            System.out.println(aniList);
        }
        return aniList;
    }

    public List<AniList> getAniList() {

        List<AniList> aniList = new AniListService().selectAllList();

        if (aniList.isEmpty()) {
            System.out.println("데이터없음!!!");
        } else {
            System.out.println(aniList);
        }
        return aniList;
    }
}
