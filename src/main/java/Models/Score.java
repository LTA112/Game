/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author lamth
 */
public class Score {

    private int account_id;
    private int score;

    public Score() {
    }

    public Score(int account_id, int score) {
        this.account_id = account_id;
        this.score = score;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getScore() {
        return score;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
