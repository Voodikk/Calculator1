package com.example.calculator;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Animation scaleDown;
    private char lastop = ' ';
    private boolean ifclear = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
    }

    public boolean ismth(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        } else {
            return false;
        }
    }

    public void clickedNumber(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if(ifclear) {
            result.setText("0");
            ifclear = false;
        }
        if(result.getText().toString().substring(lastop == ' ' ? 0 : result.getText().toString().lastIndexOf(lastop)).length() == 15) {
            Toast.makeText(MainActivity.this, "Невозможно ввести более 15 цифр", Toast.LENGTH_SHORT).show();
            return;
        }
        if (result.getText().toString().charAt(result.getText().toString().length() - 1) == '0') {
            if (result.getText().toString().length() > 1) {
                if (ismth(result.getText().toString().charAt(result.getText().toString().length() - 2))) {
                    result.setText(result.getText().toString().substring(0, result.getText().toString().length()-1) + v.getTag().toString());
                } else {
                    if (result.getText().toString().length() == 8) {
                        result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                    }
                    result.setText(result.getText().toString() + v.getTag().toString());
                }
            } else {
                result.setText(v.getTag().toString());
            }
        } else {
            if (result.getText().toString().length() == 8) {
                result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            }
            result.setText(result.getText().toString() + v.getTag().toString());
        }
    }

    public void clickedzero(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        String txt = new String(result.getText().toString());
        if(txt.substring(lastop == ' ' ? 0 : txt.lastIndexOf(lastop)).length() == 15) {
            Toast.makeText(MainActivity.this, "Невозможно ввести более 15 цифр", Toast.LENGTH_SHORT).show();
            return;
        }
        if (result.getText().toString().charAt(result.getText().toString().length() - 1) == '0') {
            if (result.getText().toString().length() > 1) {
                if (Character.isDigit(result.getText().toString().charAt(result.getText().toString().length() - 2)) || result.getText().toString().charAt(result.getText().toString().length() - 2) == '.') {
                    if (result.getText().toString().length() == 8) {
                        result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                    }
                    result.setText(result.getText().toString() + "0");
                }
            }
        } else {
            if (result.getText().toString().length() == 8) {
                result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            }
            result.setText(result.getText().toString() + "0");
        }
    }

    public void clickederase(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if (result.getText().toString().length() == 1) {
            result.setText("0");
        } else {
            result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1));
        }
        if (result.getText().toString().length() == 8) {
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        }
    }

    public void clickeddot(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if(result.getText().toString().substring(lastop == ' ' ? 0 : result.getText().toString().lastIndexOf(lastop)).length() == 15) {
            Toast.makeText(MainActivity.this, "Невозможно ввести более 15 цифр", Toast.LENGTH_SHORT).show();
            return;
        }
        if (result.getText().toString().indexOf(".") == -1) {
            if (result.getText().toString().charAt(result.getText().toString().length() - 1) == lastop) {
                if (result.getText().toString().length() == 8) {
                    result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                }
                result.setText(result.getText().toString() + "0.");
            }
            else {
                if (result.getText().toString().length() == 8) {
                    result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                }
                result.setText(result.getText().toString() + ".");
            }
        }
        else {
            if (result.getText().toString().charAt(result.getText().toString().length() - 1) == lastop) {
                result.setText(result.getText().toString() + "0.");
            }
            else if (ismth(lastop)) {
                if (result.getText().toString().substring(result.getText().toString().lastIndexOf(lastop)).indexOf(".") == -1) {
                    if (result.getText().toString().length() == 8) {
                        result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                    }
                    result.setText(result.getText().toString() + ".");
                }
            }
        }
    }

    public void clickedplus(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if (Character.isDigit(result.getText().toString().charAt(result.getText().toString().length() - 1))) {
            if (result.getText().toString().length() == 8) {
                result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            }
            result.setText(result.getText().toString() + "+");
        }
        else {
            result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1) + "+");
        }
        lastop = '+';
        ifclear = false;
    }

    public void clickedminus(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if (result.getText().toString().length() == 8) {
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        }
        if (Character.isDigit(result.getText().toString().charAt(result.getText().toString().length() - 1))) {
            result.setText(result.getText().toString() + "-");
        } else {
            result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1) + "-");
        }
        lastop = '-';
        ifclear = false;
    }

    public void clickedmultiply(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if (result.getText().toString().length() == 8) {
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        }
        if (Character.isDigit(result.getText().toString().charAt(result.getText().toString().length() - 1))) {
            result.setText(result.getText().toString() + "*");
        } else {
            result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1) + "*");
        }
        lastop = '*';
        ifclear = false;
    }

    public void clickeddivide(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        if (result.getText().toString().length() == 8) {
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        }
        if (Character.isDigit(result.getText().toString().charAt(result.getText().toString().length() - 1))) {
            result.setText(result.getText().toString() + "/");
        } else {
            result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1) + "/");
        }
        lastop = '/';
        ifclear = false;

    }

    public void clickedClear(View v) {
        v.startAnimation(scaleDown);
        TextView result = (TextView) findViewById(R.id.res);
        result.setText("0");
        result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        lastop = ' ';
    }

    public void clickedequal(View v) {
        TextView result = (TextView) findViewById(R.id.res);
        String temp = result.getText().toString();
        boolean err = false;
        if (!ismth(lastop)) {
            return;
        }
        if (temp.lastIndexOf("*") == temp.length() - 1 || temp.lastIndexOf("/") == temp.length() - 1 || temp.lastIndexOf("-") == temp.length() - 1 || temp.lastIndexOf("+") == temp.length() - 1) {
            Toast.makeText(MainActivity.this, "Использован недопустимый формат", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastop == '/' && Double.parseDouble(temp.substring(temp.lastIndexOf("/") + 1)) == 0.0) {
            Toast.makeText(MainActivity.this, "Нельзя делить на ноль", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isleft = true;
        while (temp.indexOf("+") != -1 || temp.indexOf("-") != -1 || temp.indexOf("*") != -1 || temp.indexOf("/") != -1) {
            if (temp.indexOf("*") != -1 || temp.indexOf("/") != -1) {
                int minposop = Math.min(temp.indexOf("*") == -1 ? temp.indexOf("/") : temp.indexOf("*"), temp.indexOf("/") == -1 ? temp.indexOf("*") : temp.indexOf("/"));
                int leftpos = Math.max(temp.substring(0, minposop).lastIndexOf("+") == -1 ? 0 : temp.substring(0, minposop).lastIndexOf("+") + 1, temp.substring(0, minposop).lastIndexOf("-") == -1 ? 0 : temp.substring(0, minposop).lastIndexOf("-") + 1);
                int rightpos = Math.min(Math.min(temp.substring(minposop + 1, temp.length()).indexOf("+") == -1 ? temp.length() : temp.substring(minposop + 1, temp.length()).indexOf("+"), temp.substring(minposop + 1, temp.length()).indexOf("-") == -1 ? temp.length() : temp.substring(minposop + 1, temp.length()).indexOf("-")), Math.min(temp.substring(minposop + 1, temp.length()).indexOf("*") == -1 ? temp.length() : temp.substring(minposop + 1, temp.length()).indexOf("*"), temp.substring(minposop + 1, temp.length()).indexOf("/") == -1 ? temp.length() : temp.substring(minposop + 1, temp.length()).indexOf("/")));
                if (rightpos != temp.length()) {
                    rightpos += temp.substring(0, minposop + 1).length();
                }
                double leftnum = Double.parseDouble(temp.substring(leftpos, minposop));
                double rightnum = Double.parseDouble(temp.substring(minposop + 1, rightpos));
                if (temp.charAt(minposop) == '*') {
                    temp = temp.substring(0, leftpos) + String.valueOf(leftnum * rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                } else {
                    temp = temp.substring(0, leftpos) + String.valueOf(leftnum / rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                }
            } else if (temp.indexOf("-") != -1) {
                if (temp.indexOf("-") == 0) {
                    isleft = false;
                    temp = temp.substring(1, temp.length());
                    continue;
                }
                if (temp.charAt(temp.indexOf("-") - 1) == '+') {
                    temp = temp.substring(0, temp.indexOf("-") - 1) + temp.substring(temp.indexOf("-"), temp.length());
                    continue;
                }

                int leftpos = Math.max(temp.substring(0, temp.indexOf("-")).lastIndexOf("+") + 1, 0);
                int rightpos = Math.min(temp.substring(temp.indexOf("-") + 1, temp.length()).indexOf("+") == -1 ? temp.length() : temp.substring(temp.indexOf("-") + 1, temp.length()).indexOf("+"), temp.substring(temp.indexOf("-") + 1, temp.length()).indexOf("-") == -1 ? temp.length() : temp.substring(temp.indexOf("-") + 1, temp.length()).indexOf("-"));
                if (rightpos != temp.length()) {
                    rightpos += temp.substring(0, temp.indexOf("-") + 1).length();
                }
                double leftnum = Double.parseDouble(temp.substring(leftpos, temp.indexOf("-")));
                double rightnum = Double.parseDouble(temp.substring(temp.indexOf("-") + 1, rightpos));
                if(leftpos == 0) {
                    if(isleft) {
                        temp = temp.substring(0, leftpos) + String.valueOf(leftnum - rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                    }
                    else {
                        temp = temp.substring(0, leftpos) + String.valueOf((-1) * leftnum - rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                    }
                }
                else {
                    temp = temp.substring(0, leftpos) + String.valueOf(leftnum - rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                }
            }
            else if (temp.indexOf("+") != -1) {
                int leftpos = 0;
                int rightpos = temp.substring(temp.indexOf("+") + 1, temp.length()).indexOf("+") == -1 ? temp.length() : temp.substring(temp.indexOf("+") + 1, temp.length()).indexOf("+");
                if (rightpos != temp.length()) {
                    rightpos += temp.substring(0, temp.indexOf("+") + 1).length();
                }

                double leftnum = Double.parseDouble(temp.substring(leftpos, temp.indexOf("+")));
                double rightnum = Double.parseDouble(temp.substring(temp.indexOf("+") + 1, rightpos));
                if(isleft) {
                    temp = temp.substring(0, leftpos) + String.valueOf(leftnum  + rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                }
                else {
                    temp = temp.substring(0, leftpos) + String.valueOf(leftnum * (-1) + rightnum) + temp.substring(rightpos == temp.length() ? 0 : rightpos, temp.length() == rightpos ? 0 : temp.length());
                }

                if(!isleft) {
                    isleft = leftnum * (-1) + rightnum > 0 ? true: false;
                }
            }
        }
        Double resnum = Double.parseDouble(temp);
        if(resnum % 1 == 0.0) {
            int intch = Integer.valueOf((int) Math.round(resnum));
            temp = String.valueOf(intch);
        }
        if(isleft) {
            result.setText(temp);
        }
        else {
            result.setText("-" + temp);
        }
        ifclear = true;
        lastop = ' ';
    }
}