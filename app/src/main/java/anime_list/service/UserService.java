package anime_list.service;

import java.sql.Connection;

import anime_list.config.JDBC;
import anime_list.model.dao.UserDao;
import anime_list.model.dto.UserDto;
import anime_list.model.vo.User;
public class UserService {
	public int insertUser(User user) {
			// 1) Connection 객체 생성 (jdbc driver 등록 포함)
			Connection conn = JDBC.getConnection();
		   
			// 2) Member객체 dao 전달하여 데이터 처리에 대한 결과 받기
			int result = new UserDao().insertUser(conn,user);
			// 3) 데이터 추가/수정/삭제(DML) 작업 시 트레잭션 처리
			if(result>0) { 		//회원 추가 성공
				JDBC.commit(conn);	
			}else {				// 회원 추가 실패
				JDBC.rollback(conn);
			}
			
			JDBC.close(conn);	//자원 반납
			return result;
		}

		public User loginUser(String userId,String passwd) {

			Connection conn = JDBC.getConnection();
			User user = new UserDao().loginUser(conn, userId, passwd);
			JDBC.close(conn);
			return user;
		}

        public int updateUser(User user) {
            
            Connection conn = JDBC.getConnection();

			int  result = 0;
			result = new UserDao().updateUser(conn,user);

			
			if(result>0){
				JDBC.commit(conn);
			}else{
				JDBC.rollback(conn);
			}

			JDBC.close(conn);

			return result;

        }

        public UserDto idchUser(String userId) {
            Connection conn = JDBC.getConnection();
			UserDto userdto = new UserDao().idch(conn,userId);
			JDBC.close(conn);
			return userdto;
        }


    

	


		
}