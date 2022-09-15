package fitIn.fitInserver.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "bookmark")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    private String bookmarkName;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "bookmark", cascade = CascadeType.ALL)
    private List<BookmarkSave> bookmarkSaves = new ArrayList<>();

    private LocalDateTime bookmarkDate;

    public void setAccount(Account account){
        this.account = account;
        account.getBookmark().add(this);
    }
    public void addBookmarkSave(BookmarkSave bookmarkSave){
        bookmarkSaves.add(bookmarkSave);
        bookmarkSave.setBookmark(this);
    }


    public static Bookmark addBookmark(Bookmark bookmark, BookmarkSave... bookmarkSaves){
        for(BookmarkSave bookmarkSave : bookmarkSaves){
            bookmark.addBookmarkSave(bookmarkSave);
        }
        bookmark.setBookmarkDate(LocalDateTime.now());
        return bookmark;
    }
    public static Bookmark createBookmark(Account account, String bookmarkName){
        Bookmark bookmark = new Bookmark();
        bookmark.setAccount(account);
        bookmark.setBookmarkName(bookmarkName);
        bookmark.setBookmarkDate(LocalDateTime.now());
        return bookmark;
    }


}
