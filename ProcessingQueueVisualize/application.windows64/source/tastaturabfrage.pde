String inputText = "";
boolean overText = false;
void keyPressed() {
  if (overText) {

    if (key == ENTER) {
      enqueue(inputText);
    } else if (key == BACKSPACE &&inputText.length()>0) {
      inputText = inputText.substring(0, inputText.length()-1);
    } else if (key == DELETE &&inputText.length()!=0) {
      inputText = "";
    } else
      inputText = inputText + key;
  }
}


void drawInputField() {
  if (overText) fill(100, 149, 237);
  else fill(255, 255, 255);
  rect(20, 80, 380, 40);
  fill(0, 0, 0);
  textSize(18);
  textAlign(LEFT, TOP);
  text(inputText, 22, 100);
}

void clearText() {
  inputText = "";
}