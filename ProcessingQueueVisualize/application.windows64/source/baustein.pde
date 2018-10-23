class Baustein {
  int position = 0;
  String Inhalt = "";
  boolean mausDrauf = false;
  color farbe = color(r.nextInt(127), r.nextInt(127), r.nextInt(127));
  Baustein(String pinhalt) {
    Inhalt = pinhalt;
  }
  void drawBaustein(int pos) {
    position = pos;    
    fill(farbe);
    rect((pos*50)+scroll, 200, 60, 50);
  }
  void texti() {
    if (overRect((pos*50)+scroll, 200, 60, 50)) {
      fill(255, 255, 255);
      rect((pos*50)+scroll, 175, 200, 25);

      textSize(15);
      textAlign(LEFT, TOP);
      fill(farbe);
      text(Inhalt, (pos*50)+scroll+5, 180);
    }
  }
}