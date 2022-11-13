package com.keegan.android.friends;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class FriendButton extends AppCompatButton {
    private Friend friend;

    public FriendButton(Context context, Friend newFriend)
    {
        super(context);
        friend = newFriend;
    }


}
