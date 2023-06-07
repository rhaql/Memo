package entity;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MemoList {
    private LinkedList<Memo> list;
    private Scanner sc;

    public MemoList() {
        this.list = new LinkedList<>();
        sc = new Scanner(System.in);
    }

    // 메모 리스트 전체 크기 반환
    public int getSize() { return list.size(); }

    // 작성 최신순 메모 출력
    public void printMemoList() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------- [ Memo List ] ---------------------\n");
        sb.append("Post Number | Writer Name | Content         | Written Time\n");

        // 작성 최신 순으로 메모를 출력한다.
        ListIterator<Memo> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            Memo memo = it.previous();
            sb.append(String.format(" %10d | %11s | %-15s | %s\n", memo.getNum(), memo.getName(), memo.getPost(), memo.getTime()));
        }

        sb.append("----------------------------------------------------------\n");
        System.out.print(sb);
    }

    // 메모 1건 추가
    public void addMemo(Memo memo){
        // 기존 list에 Memo 추가
        list.add(memo);
    }

    // 글 수정/삭제 시, 글 번호를 받아서 해당 메모를 반환
    public Memo getMemo(int idx) {
        // list에서 idx에 해당하는 Memo 반환하기
        return list.get(idx);
    }

    // 메모 1건 수정 (구현)
    public void editMemo(int idx) {
        // (1) list에서 idx에 해당하는 Memo 가져오기
        Memo memo = list.get(idx -1);
        // (2) 해당 Memo 내용 출력하기
        System.out.println("-----------------");
        System.out.println("수정할 게시글 번호 : ");
        System.out.println(memo.getPost());
        System.out.println("-----------------");

        // (3) scanner로 수정할 내용 입력받기
        System.out.print("수정할 내용을 입력하세요 : ");
        String newPost = sc.nextLine();
        // (4) Memo의 게시글(post) 필드 갱신
        memo.setPost(newPost);
        System.out.println("메모가 수정되었습니다.");
    }

    // 메모 1건 삭제 (구현)
    public void deleteMemo(int idx) {
        // (1) list에서 idx에 해당하는 Memo 가져오기
        Memo memo = list.get(idx -1);
        // (2) list에서 해당 memo를 제거
        list.remove(idx -1);

        editMemoNum(idx -1); // (3) 글 삭제 후 글 번호 재지정 작업
        System.out.println("메모가 삭제되었습니다.");
    }

    // 글 삭제 후 Memo의 num 필드 수정 (구현)
    public void editMemoNum(int idx){
        // for 문으로 idx 부터 list.size()까지의 memo들의 num을 수정
        for (int i = idx; i < list.size(); i++) {
            Memo memo = list.get(i);
            memo.setNum(i + 1);
        }
    }
}
