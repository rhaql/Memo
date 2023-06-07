package entity;

import java.util.*;

public class Memo {
    private int num;        // 글 번호
    private String name;    // 작성자 이름
    private String password;// 비밀번호
    private String post;    // 게시글
    private String time;    // 작성일 (컴퓨터 시스템 시간 기준)

    public Memo(int num, String name, String password, String post) {
        this.num = num;
        this.name = name;
        this.password = password;
        this.post = post;
        time = dateFormat(Calendar.getInstance());
    }

    public int getNum() { return num; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getPost() { return post; }

    public String getTime() { return time; }

    // 글 수정 작업
    public void setPost(String post) {
        this.post = post;                           // post(내용) 변경
        time = dateFormat(Calendar.getInstance());  // time(시간)을 새로 수정
    }

    // 글 삭제 후 글 번호 재지정
    public void setNum(int num) {
        this.num = num;
    }

    // 작성일 형식 반환 함수
    public String dateFormat(Calendar today) {
        return today.get(Calendar.YEAR)+"-" + (today.get(Calendar.MONTH)+1)+"-"
                + today.get(Calendar.DATE)+"-" + today.get(Calendar.HOUR)+":"
                + today.get(Calendar.MINUTE)+":" + today.get(Calendar.SECOND);
    }

    public void printMemo() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------\n");
        sb.append(String.format("num: %s\n", num));
        sb.append(String.format("name: %s\n", name));
        sb.append(String.format("post: %s\n", post));
        sb.append("-----------------\n");
        System.out.print(sb);
    }
}
