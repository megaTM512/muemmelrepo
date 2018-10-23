import java.util.Random;

Random r = new Random();

List bausteine = new List();
int scroll = 5;
int scrollwert = 30;
int pos = 0;
Button enqueueButton = new Button(20, 20, 120, 60, color(85, 107, 47), "enqueue(Inhalt);", color(255, 255, 255));
Button dequeueButton = new Button(140, 20, 100, 60, color(165, 42, 42), "dequeue();", color(255, 255, 255));
Button isEmpty = new Button(240, 20, 80, 30, color(72, 61, 139), "isEmpty();", color(255, 255, 255));
Button front = new Button(320, 20, 80, 30, color(47, 79, 79), "front();", color(255, 255, 255));
Button scrollLeft = new Button(240, 50, 80, 30, color(255, 20, 147), "<<<", color(255, 255, 255));
Button scrollRight = new Button(320, 50, 80, 30, color(255, 20, 147), ">>>", color(255, 255, 255));

color bgcol = color(240, 128, 128);

void setup() {
  PFont calibri = createFont("Calibri", 32, true);
  size(800, 400);
  frameRate(60);
  textFont(calibri);
}

void draw() {
  background(bgcol);
  fill(255,255,255);
  textSize(30);
  text("Queue Tool 0.7 von Tim Moeller",410,20);
  textSize(20);
  text("Tool um Warteschlangen in Java zu visualisieren",410,50);
  text("1.03.2018",410,70);
  enqueueButton.drawButton();
  dequeueButton.drawButton();
  isEmpty.drawButton();
  front.drawButton();
  scrollLeft.drawButton();
  scrollRight.drawButton();
  checkMouse();
  drawInputField();
  frontShow();
  pos = 0;
  bausteine.toFirst();
  while (bausteine.hasAccess()) {
    bausteine.getContent().drawBaustein(pos);
    bausteine.getContent().texti();
    bausteine.next();
    pos++;
  }
}

void frontShow() {
  bausteine.toFirst();
  if (bausteine.hasAccess()) {
    fill(bausteine.getContent().farbe);
    rect((bausteine.getContent().position*50)+scroll, 260, 50, 20);
    textSize(12);
    fill(255, 255, 255);
    textAlign(LEFT, TOP);
    text("First Item", (bausteine.getContent().position*50)+scroll, 260);
  }
}

void enqueue(String txt) {
  Baustein bs = new Baustein(txt);
  bausteine.append(bs);
  clearText();
}

void isEmpty() {
  if (!bausteine.isEmpty()) inputText = "false";
  else inputText = "true";
}

void dequeue() {
  bausteine.toFirst();
  if (bausteine.hasAccess())bausteine.remove();
}

void front() {
  bausteine.toFirst();
  if (bausteine.hasAccess())inputText = bausteine.getContent().Inhalt;
  else inputText = "null";
  scroll = 5;
}

void scrollLeft() {
  scroll=scroll+scrollwert;
}

void scrollRight() {
  scroll=scroll-scrollwert;
}