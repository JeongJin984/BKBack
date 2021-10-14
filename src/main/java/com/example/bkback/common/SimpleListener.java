package com.example.bkback.common;

import com.example.bkback.db.entity.*;
import jdk.jfr.Category;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    private EntityManagerFactory entityManagerFactory;
    private PasswordEncoder passwordEncoder;
    private static Long count = 0L;

    @Autowired
    public SimpleListener(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManagerFactory = entityManagerFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        if (count != 0L) {
            return;
        }
        count = 1L;

        System.out.println("count = " + count);

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Account account1 = new Account("JJ NAM", passwordEncoder.encode("pass"), null,new Date(), "paka.png");
        Account account2 = new Account("test2", passwordEncoder.encode("pass"), null,new Date(), "paka.png");
        Account account3 = new Account("test3", passwordEncoder.encode("pass"), null,new Date(), "paka.png");

        em.persist(account1);
        em.persist(account2);
        em.persist(account3);

        Clan clan1 = new Clan("testClan1", account1.getUsername());
        Clan clan2 = new Clan("testClan2", account2.getUsername());

        em.persist(clan1);
        em.persist(clan2);

        AccountClan accountClan1 = new AccountClan(account1, clan1, account1.getUsername(), true);
        AccountClan accountClan2 = new AccountClan(account2, clan1, account2.getUsername(), false);
        AccountClan accountClan3 = new AccountClan(account3, clan2, account2.getUsername(), true);
        AccountClan accountClan4 = new AccountClan(account2, clan2, account2.getUsername(), false);

        em.persist(accountClan1);
        em.persist(accountClan2);
        em.persist(accountClan3);
        em.persist(accountClan4);

        PostCategory category = new PostCategory("category");

        em.persist(category);

        Hashtag hashtag1 = new Hashtag("test");
        Hashtag hashtag2 = new Hashtag("test2");
        Hashtag hashtag3 = new Hashtag("test3");

        em.persist(hashtag1);
        em.persist(hashtag2);
        em.persist(hashtag3);

        Post post1 = new Post("postImage1", "paka.png", "content1", 1L,account1, category, null);
        Post post2 = new Post("postImage2", "paka.png", "content2", 2L,null, category, clan1);

        em.persist(post1);
        em.persist(post2);

        Comment comment1 = new Comment("paka.png", account1.getUsername(), "comment1", null, 3L, post1, account1, false);
        Comment comment4 = new Comment("paka.png", account3.getUsername(), "comment4", null, 4L, post1, account3, false);

        em.persist(comment1);

        Comment comment2 = new Comment("paka.png", account2.getUsername(), "comment2", comment1, 1L, post1, account2, true);
        Comment comment3 = new Comment("paka.png", account1.getUsername(), "comment3", comment1, 2L, post1, account1, true);

        em.persist(comment2);
        em.persist(comment3);
        em.persist(comment4);

        PostHashtag postHashtag1 = new PostHashtag(hashtag1, post1);
        PostHashtag postHashtag2 = new PostHashtag(hashtag2, post1);
        PostHashtag postHashtag3 = new PostHashtag(hashtag3, post1);
        PostHashtag postHashtag4 = new PostHashtag(hashtag1, post2);
        PostHashtag postHashtag5 = new PostHashtag(hashtag3, post2);

        em.persist(postHashtag1);
        em.persist(postHashtag2);
        em.persist(postHashtag3);
        em.persist(postHashtag4);
        em.persist(postHashtag5);

        Follow follow1 = new Follow(account2, account2.getUsername(), account1, account1.getUsername());
        Follow follow2 = new Follow(account1, account1.getUsername(), account2, account2.getUsername());
        Follow follow3 = new Follow(account2, account2.getUsername(), account3, account3.getUsername());

        em.persist(follow1);
        em.persist(follow2);
        em.persist(follow3);

        Intimacy intimacy1 = new Intimacy(account1, account1.getUsername(), 2L, account2, account2.getUsername(), 2L);
        Intimacy intimacy2 = new Intimacy(account2, account2.getUsername(), 1L, account3, account3.getUsername(), 0L);
        Intimacy intimacy3 = new Intimacy(account1, account1.getUsername(), 2L, account3, account3.getUsername(), 4L);

        em.persist(intimacy1);
        em.persist(intimacy2);
        em.persist(intimacy3);

        LikedPost likedPost1 = new LikedPost(account1 ,post1);
        LikedPost likedPost2 = new LikedPost(account2 ,post1);
        LikedPost likedPost3 = new LikedPost(account3 ,post1);
        LikedPost likedPost4 = new LikedPost(account1 ,post2);
        LikedPost likedPost5 = new LikedPost(account3 ,post2);

        em.persist(likedPost1);
        em.persist(likedPost2);
        em.persist(likedPost3);
        em.persist(likedPost4);
        em.persist(likedPost5);

        LikedComment likedComment1 = new LikedComment(comment1, account1);
        LikedComment likedComment2 = new LikedComment(comment1, account2);
        LikedComment likedComment3 = new LikedComment(comment1, account3);
        LikedComment likedComment4 = new LikedComment(comment2, account2);
        LikedComment likedComment5 = new LikedComment(comment2, account3);

        em.persist(likedComment1);
        em.persist(likedComment2);
        em.persist(likedComment3);
        em.persist(likedComment4);
        em.persist(likedComment5);

        Postlist postlist1 = new Postlist("postlist1", account1);
        Postlist postlist2 = new Postlist("postlist2", account1);
        Postlist postlist3 = new Postlist("postlist3", account1);
        Postlist postlist4 = new Postlist("postlist4", account2);
        Postlist postlist5 = new Postlist("postlist5", account2);
        Postlist postlist6 = new Postlist("postlist1", account3);

        em.persist(postlist1);
        em.persist(postlist2);
        em.persist(postlist3);
        em.persist(postlist4);
        em.persist(postlist5);
        em.persist(postlist6);

        PostPostList postPostList1 = new PostPostList(post1, post1.getTitle(), post1.getImage(), postlist1, postlist1.getName());
        PostPostList postPostList2 = new PostPostList(post2, post1.getTitle(), post1.getImage(), postlist1, postlist1.getName());
        PostPostList postPostList3 = new PostPostList(post1, post1.getTitle(), post1.getImage(), postlist2, postlist2.getName());
        PostPostList postPostList4 = new PostPostList(post2, post2.getTitle(), post1.getImage(), postlist2, postlist2.getName());
        PostPostList postPostList5 = new PostPostList(post1, post1.getTitle(), post1.getImage(), postlist3, postlist3.getName());

        em.persist(postPostList1);
        em.persist(postPostList2);
        em.persist(postPostList3);
        em.persist(postPostList4);
        em.persist(postPostList5);

        transaction.commit();
        em.close();
    }
}