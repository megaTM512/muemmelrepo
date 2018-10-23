class Button {
  float xp, xl, yp, yl;
  String text;
  color buttoncolor, textcolor;
  Button(float px, float py, float pw, float ph, color colt, String label, color textcol) {
    xp = px;
    xl = pw;
    yp = py;
    yl = ph;
    buttoncolor = colt;
    text = label;
    textcolor = textcol;
  }
  void drawButton() {
    fill(buttoncolor);
    rect(xp, yp, xl, yl);
    textSize(15);
    textAlign(CENTER, CENTER);
    fill(textcolor);
    text(text, xp+xl/2, yp+yl/2);
  }
}