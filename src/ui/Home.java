package ui;

import entity.*;

import java.util.Scanner;

public class Home {
    private Scanner sc;
    private MemoList memoList;

    public Home() {
        sc = new Scanner(System.in);
        memoList = new MemoList();
    }

    public void start() {
        while(true) {
            // 0. 메모장 옵션 출력
            printOptions();
            int choice = selectNum();   // 옵션 번호 입력받기

            switch(choice) {
                // 1. 입력
                case 1 -> {
                    Memo memo = writeMemo();    // 이름, 비밀번호, 메모 입력받기
                    memoList.addMemo(memo);     // 글 생성 후 메모리스트에 저장
                }

                // 2. 목록 보기
                case 2 -> {
                    if(checkEmpty()) break;
                    memoList.printMemoList(); // 작성된 메모 최신순으로 출력하기
                }

                // 3. 수정
                case 3 -> {
                    editMemo();
                }

                // 4. 삭제 (구현)
                case 4 -> {
                    deleteMemo();
                }

                // 5. 종료
                case 5 -> {
                    System.out.println("메모장을 종료합니다.");
                    System.exit(0);
                }
            }
        }
    }

    // 0. 메모장 옵션 출력
    public void printOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------\n");
        sb.append("[메모장 시작 페이지]\n");
        sb.append("\t1. 입력\n\t2. 목록 보기\n\t3. 수정\n\t4. 삭제\n\t5. 종료\n");
        sb.append("-----------------\n");
        System.out.print(sb);
    }

    // 0-1. 번호 입력받기
    public int selectNum() {
        System.out.print("번호를 입력해주세요: ");
        int num = sc.nextInt();
        sc.nextLine(); // '\n'
        return num;
    }

    // 1. 메모 작성하기
    public Memo writeMemo() {
        // (1) 글 번호 불러오기
        int num = memoList.getSize() + 1;

        System.out.print("-----------------\n");
        System.out.print("작성자 이름: ");   // (2) 이름 입력
        String name = sc.next();
        System.out.print("비밀번호: ");     // (3) 비밀번호 입력
        String password = sc.next();
        System.out.print("내용: ");        // (4) 내용 입력
        String post = sc.next();
        System.out.print("-----------------\n");

        // (5) 입력된 내용 Memo 생성자로 전달 후 반환
        return new Memo(num, name, password, post);
    }

    // ex-1. 메모 리스트 비었는지 확인
    public boolean checkEmpty() {
        if(memoList.getSize() == 0) {
            System.out.println("메모장이 비었습니다.");
            return true;
        }
        return false;
    }

    // ex-2. 메모 비밀번호 확인
    public boolean checkPassword(int choice) {
        Memo memo = memoList.getMemo(choice-1);

        int chance = 3;
        while(chance > 0) {
            System.out.printf("비밀번호 확인(기회 %d 번) : ", chance--);
            if(memo.getPassword().equals(sc.next())) return false;
        }

        return true;
    }

    // 3. 메모 편집하기
    public void editMemo() {
        if(checkEmpty()) return;    // memoList가 비었는지 확인

        memoList.printMemoList();   // memoList 출력

        // (수정할) 글 번호 입력 받기
        int choice = 0;
        while((choice = selectNum()) > memoList.getSize())
            System.out.println("번호에 맞는 글이 존재하지 않습니다.");

        // 비밀 번호 확인. 일치 시, 내용 수정. 불일치 시, 메시지 출력
        if(checkPassword(choice)) return;
        memoList.editMemo(choice);


    }

    // 4. 메모 삭제하기
    public void deleteMemo() {
        if(checkEmpty()) return;    // memoList가 비었는지 확인

        memoList.printMemoList();   // memoList 출력

        // (삭제할) 글 번호 입력 받기
        int choice = 0;
        while((choice = selectNum()) > memoList.getSize())
            System.out.println("번호에 맞는 글이 존재하지 않습니다.");

        // 비밀 번호 확인. 일치 시, 내용 수정. 불일치 시, 메시지 출력
        if(checkPassword(choice)) return;
        memoList.deleteMemo(choice);

    }
}
