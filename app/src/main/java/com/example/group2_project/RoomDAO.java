package com.example.group2_project;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    public void Insert(UsernamePassword... usernamePasswords);

    @Update
    public void Update(UsernamePassword... usernamePasswords);



    @Query("Select * from login where usename = :username")
    public UsernamePassword getUserwithUsername(String username);

    @Query("Select * from login where isloggedIn = 1")
    public UsernamePassword getLoggedInUser();


    @Insert
    public void Insert(Reminders reminders);

    @Update
    public void Update(Reminders... reminders);

    @Delete
    public void Delete(Reminders reminders);

    @Query("Select * from reminder order by remindDate")
    public List<Reminders> orderThetable();

    @Query("Select * from reminder Limit 1")
    public Reminders getRecentEnteredData();

    @Query("Select * from reminder")
    public List<Reminders> getAll();

}
