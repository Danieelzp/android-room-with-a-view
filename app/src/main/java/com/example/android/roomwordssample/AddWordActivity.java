package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for entering a word.
 */

public class AddWordActivity extends AppCompatActivity {
    public static final String EXTRA_WORD =
            "com.example.android.roomwordssample.EXTRA_WORD";

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_WORD)){
            mEditWordView.setText(intent.getStringExtra(EXTRA_WORD));
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent data = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, data);
                } else {
                    String word = mEditWordView.getText().toString();
                    data.putExtra(EXTRA_WORD, word);
                    setResult(RESULT_OK, data);
                }
                finish();
            }
        });

    }

    private void saveWord(){
        String word = mEditWordView.getText().toString();

        if(word.trim().isEmpty()){
            Toast.makeText(this,"Inserte una palabra", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_WORD, word);

        setResult(RESULT_OK, data);
        finish();
    }
}

