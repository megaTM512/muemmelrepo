import java.util.Random; //<>//
import controlP5.*; //danke cp5!
Random r = new Random();



String inhalt = "";
String Top ="";

color geilesWeiss = color(255, 255, 255);
color geilesBlau = color(0, 139, 139);
color geilesGelb = color(138, 138, 0);
color geilesRot = color(139, 0, 0);

stackElement[] stuff = new stackElement[21];
int counter = 0;

boolean overPush = false;
boolean overPop = false;
boolean overTop = false;
boolean overMode = false;

button pushb = new button(20, 100, 80, 40, geilesBlau, "push(Inhalt);", geilesWeiss);
button popb = new button(20, 100, 140, 40, geilesRot, "pop();", geilesWeiss);
button textmode = new button(20, 40, 300, 40, geilesRot, "X", geilesWeiss);
button isemptyb;
button top = new button(20, 100, 200, 40, geilesGelb, "top();", geilesWeiss);
button up;
button down;
button godmode;
ControlP5 cp5;
boolean isGodmode = true;



void setup() {
  PFont font = createFont("Calibri", 32, true);
  size(500, 500);
  frameRate(60);
  background(222, 184, 135);  //geiles Besch
  textFont(font);

  cp5 = new ControlP5(this);
  cp5.addTextfield("Inhalt").setPosition(20, 20).setSize(100, 40).setFont(font).setFocus(true).setColor(color(255, 255, 255));
}

class button {
  float xp, xl, yp, yl;
  String text;
  color buttoncolor, textcolor;
  button (float xpt, float xlt, float ypt, float ylt, color colt, String label, color textcol) {
    xp = xpt;
    xl = xlt;
    yp = ypt;
    yl = ylt;
    buttoncolor = colt;
    text = label;
    textcolor = textcol;
  }
  void drawbutton() {
    fill(buttoncolor);
    rect(xp, yp, xl, yl);
    textSize(15);
    textAlign(CENTER, CENTER);
    fill(textcolor);
    text(text, xp+xl/2, yp+yl/2);
  }
}

class stackElement {
  String inhalt = "undefined";
  int position;
  color farbe = color(r.nextInt(127), r.nextInt(127), r.nextInt(127));
  stackElement(int posi, String content) {
    position = posi;
    inhalt = content;
  }
  void drawElement() {
    fill(farbe);
    rect(190, (position*-25)+height-26, 60, 25);
    if (isGodmode) {
      textSize(20);
      textAlign(LEFT, CENTER);
      text("-> " + inhalt, 260, (position*-25)+height-16);
    }
  }
}

void push() {
  if (counter<stuff.length) {
    stuff[counter] = new stackElement(counter, cp5.get(Textfield.class, "Inhalt").getText());
  }
  counter++;
  cp5.get(Textfield.class, "Inhalt").submit();
}
void pop() {
  if (counter>0) {
    stuff[counter-1] = null;    
    if (counter!=0) {
      counter--;
    }
  } else if (counter == 0) {
    stuff[0] = null;
  }
}

void top() {
  if(counter == 0 && stuff[0]!=null){
    Top = stuff[0].inhalt;
  }
  else if(counter>0 && stuff[counter-1]!=null){
    Top = stuff[counter-1].inhalt;
  }

   else {
    Top = "null";
  }
}

boolean overRect(int x, int width, int y, int height) {
  if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}

void update() {
  if (overRect(20, 100, 80, 40)) overPush = true;
  else overPush = false;
  if (overRect(20, 100, 140, 40)) overPop = true;
  else overPop = false;
  if (overRect(20, 100, 200, 40)) overTop = true;
  else overTop = false;
  if (overRect(20, 40, 300, 40)) overMode = true;
  else overMode = false;
}

void textModes(){
  if (isGodmode){
    isGodmode = false;
  }
  if (!isGodmode){
    isGodmode = true;
  }
}

void mousePressed() {
  if (overPush) {
    push();
  }
  if (overPop) {
    pop();
  }
  if (overTop) {
    top();
  }
  if (overMode){
    textModes();
  }
}

void draw() {
  background(222, 184, 135);
  update();
  pushb.drawbutton();
  top.drawbutton();
  textmode.drawbutton();
  rect(20, 240, 100, 40);
  textAlign(LEFT, CENTER);
  if (stuff[counter]!=null) fill(stuff[counter].farbe);
  else fill(0, 0, 0);
  text(Top, 22, 260);
  popb.drawbutton();
  for (int i = 0; i <stuff.length; i++) {
    if (stuff[i] != null) {
      stuff[i].drawElement();
    }
    System.out.println("he");
  }
}