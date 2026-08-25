package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AoCGame extends ApplicationAdapter implements InputProcessor {
   public static final boolean STEAM_BUILD = false;
   public Touch touch = new Touch();
   public static boolean screenShotMode = false;
   public static OrthographicCamera camera;
   public static Viewport viewport;
   public SpriteBatch oSB;
   public static LinkHandler mLinkHandler;
   public long lTimeFPS;
   public int iNumOfFPS = 0;
   public static boolean drawFPS = false;
   public AoCGame.RequestRendering requestRendering;
   public static ShaderProgram defaultShader;
   public static ShaderProgram blackWhiteShader;
   public static ShaderProgram nextPlayerTurnShader;
   public static ShaderProgram shaderAlpha;
   public static ShaderProgram shaderAlpha2;
   public final String VERTEX = "attribute vec4 a_position;attribute vec4 a_color;attribute vec2 a_texCoord0;uniform mat4 u_projTrans;varying vec4 vColor;varying vec2 vTexCoord;void main() {\tvColor = a_color;\tvTexCoord = a_texCoord0;\tgl_Position =  u_projTrans * a_position;}";
   public String vertexShader;
   public String fragmentShader;
   public static int FPS_LIMIT = 60;
   public long renderStart;
   public boolean MAP_MOVE_LEFT = false;
   public boolean MAP_MOVE_RIGHT = false;
   public boolean MAP_MOVE_TOP = false;
   public boolean MAP_MOVE_BOT = false;
   public static final int DEFAULT_SCROLL_MAP = 12;
   public float iScroll_MAP = 12.0F;
   public long lScrollTime_MAP = 0L;
   public float iScroll_MAPY = 12.0F;
   public long lScrollTime_MAPY = 0L;
   public static final int TYPE_NUMBER_RESET_TIME = 625;
   public static long TYPE_NUMER_TIME = 0L;
   public static int TYPE_NUMBER = 0;
   public static final int DEFAULT_SCROLL = 15;
   public int iScroll = 15;
   public long lScrollTime = 0L;
   private SpriteBatch mod;

   public AoCGame(LinkHandler nLinkHandler) {
      mLinkHandler = nLinkHandler;
   }

   public AoCGame() {
   }

   public final void updateRequestRendering(boolean enable) {
      if (enable) {
         this.requestRendering = new AoCGame.RequestRendering() {
            @Override
            public void update() {
            }
         };
         CFG.setRender_3(true);
      } else {
         this.requestRendering = new AoCGame.RequestRendering() {
            @Override
            public void update() {
            }
         };
      }
   }

   @Override
   public void create() {
      ConfigINI.readConfig();
      CFG.LANDSCAPE = ConfigINI.landscape;
      if (CFG.isAndroid()) {
         if (CFG.LANDSCAPE) {
            CFG.GAME_WIDTH = Gdx.graphics.getWidth();
            CFG.GAME_HEIGHT = Gdx.graphics.getHeight();
         } else if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth()) {
            CFG.GAME_WIDTH = Gdx.graphics.getHeight();
            CFG.GAME_HEIGHT = Gdx.graphics.getWidth();
         } else {
            CFG.GAME_WIDTH = Gdx.graphics.getWidth();
            CFG.GAME_HEIGHT = Gdx.graphics.getHeight();
         }
      } else {
         CFG.GAME_WIDTH = Gdx.graphics.getWidth();
         CFG.GAME_HEIGHT = Gdx.graphics.getHeight();
      }

      if (CFG.isDesktop()) {
         Plusic.initPlusic();
      }

      camera = new OrthographicCamera(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
      camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
      viewport = new FitViewport(CFG.GAME_WIDTH, CFG.GAME_HEIGHT, camera);
      CFG.updateRender(true);
      this.updateRequestRendering(true);
      CFG.loadSettings();
      CFG.DENSITY = Gdx.graphics.getDensity();
      if (CFG.DENSITY < 1.0F) {
         CFG.DENSITY = 1.0F;
      }

      if (ConfigINI.iUIScale <= 0) {
         if (CFG.isAndroid()) {
            CFG.XHDPI = Gdx.graphics.getPpiX() >= 300.0F || CFG.GAME_WIDTH >= 1200 || CFG.GAME_HEIGHT >= 1200;
            CFG.XXHDPI = Gdx.graphics.getPpiX() >= 380.0F || CFG.GAME_WIDTH >= 1800 || CFG.GAME_HEIGHT >= 1800;
         } else if (CFG.isDesktop()) {
            CFG.XHDPI = CFG.GAME_WIDTH >= 2400;
         }
      } else if (ConfigINI.iUIScale == 1) {
         CFG.XHDPI = false;
         CFG.XXHDPI = false;
         CFG.XXXHDPI = false;
         CFG.XXXXHDPI = false;
      } else if (ConfigINI.iUIScale == 2) {
         CFG.XHDPI = true;
         CFG.XXHDPI = false;
         CFG.XXXHDPI = false;
         CFG.XXXXHDPI = false;
      } else if (ConfigINI.iUIScale == 3) {
         CFG.XHDPI = true;
         CFG.XXHDPI = true;
         CFG.XXXHDPI = false;
         CFG.XXXXHDPI = false;
      } else if (ConfigINI.iUIScale == 4) {
         CFG.XHDPI = true;
         CFG.XXHDPI = true;
         CFG.XXXHDPI = true;
         CFG.XXXXHDPI = false;
      } else if (ConfigINI.iUIScale == 5) {
         CFG.XHDPI = true;
         CFG.XXHDPI = true;
         CFG.XXXHDPI = true;
         CFG.XXXXHDPI = true;
      }

      this.oSB = new SpriteBatch();
      this.mod = new SpriteBatch();
      Gdx.input.setInputProcessor(this);
      Gdx.input.setCatchBackKey(true);
      Images.btn_menu_h = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "buttons/menu.png");
      Images.btn_clear = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "buttons/clear.png");
      Images.btn_close = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "buttons/close.png");
      Images.diplo_war = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "icons/war.png");
      Images.line_32_off1 = ImageManager.addImage("UI/lines/line_32_off1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
      Images.gradient = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "gradient.png");
      Images.loading_rect_edge = ImageManager.addImage(
         "UI/" + CFG.getRescouresPath() + "loading/loading_edge.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge
      );
      Images.pix255_255_255 = ImageManager.addImage("UI/pix", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
      CFG.BUTTON_HEIGHT = ImageManager.getImage(Images.btn_menu_h).getHeight();
      CFG.BUTTON_WIDTH = CFG.XXXXHDPI ? 212 : (CFG.XXXHDPI ? 180 : (CFG.XXHDPI ? 160 : (CFG.XHDPI ? 120 : 90)));
      CFG.GUI_SCALE = 100.0F * CFG.BUTTON_HEIGHT / 68.0F / 100.0F;
      CFG.PADDING = (int)(5.0F * CFG.GUI_SCALE);
      CFG.CIV_INFO_MENU_WIDTH = (int)(CFG.CIV_INFO_MENU_WIDTH * CFG.GUI_SCALE);
      CFG.MENU_WIDTH_ACTION_ARMY = (int)(CFG.MENU_WIDTH_ACTION_ARMY * (CFG.GUI_SCALE * 0.93));
      CFG.CIV_COLOR_WIDTH = (int)(CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE);
      CFG.SERVICE_RIBBON_WIDTH = (int)(CFG.SERVICE_RIBBON_WIDTH * CFG.GUI_SCALE);
      CFG.SERVICE_RIBBON_HEIGHT = (int)(CFG.SERVICE_RIBBON_HEIGHT * CFG.GUI_SCALE);
      if (CFG.settingsManager.FONT_MAIN_SIZE < 0) {
         CFG.settingsManager.FONT_MAIN_SIZE = (int)(18.0F * CFG.GUI_SCALE);
      }

      updateArmyFontSize();
      Images.gameLogo = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "game_logo.png");
      Images.gameLogo = ImageManager.addImage("UI/" + CFG.getRescouresPath() + "game_logo.png");
      File folder = new File("UI/bg/");
      final File[] bgFiles = folder.listFiles();
      if (bgFiles != null && bgFiles.length > 0) {
         for (int i = 0; i < bgFiles.length; i++) {
            if (bgFiles[i].isFile()) {
               Images.backgrounds.add(ImageManager.addImage(bgFiles[i].getPath()));
            }
         }

         Images.background = ThreadLocalRandom.current().nextInt(0, bgFiles.length);
      }

      Images.backgroundLast = Images.background;
      Thread backgroundChanger = new Thread(new Runnable() {
         private volatile boolean running = true;

         @Override
         public void run() {
            while (this.running) {
               try {
                  TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10, 16));
                  synchronized (Images.class) {
                     Images.backgroundLast = Images.background;
                     Images.background = ThreadLocalRandom.current().nextInt(0, bgFiles.length);
                  }

                  for (float alpha = 0.0F; alpha < 1.0F; alpha += 0.01F) {
                     synchronized (Images.class) {
                        Images.backgroundAlpha = alpha;
                     }

                     TimeUnit.MILLISECONDS.sleep(25L);
                  }
               } catch (InterruptedException var6) {
                  this.running = false;
                  Thread.currentThread().interrupt();
               }
            }
         }

         public void stop() {
            this.running = false;
         }
      });
      backgroundChanger.start();
      CFG.menuManager = new MenuManager();
      Game_Render.updateRenderer();
      Game_Render.updateDrawMoveUnits();
      CFG.soundsManager = new SoundsManager();

      try {
         if (!CFG.soundsManager.getlTitles().isEmpty()) {
            Gdx.app.log("AoC", "MUSIC AUTO: starting autoplay");
            CFG.soundsManager.startAutoplay();
         } else {
            Gdx.app.log("AoC", "MUSIC AUTO: no titles!");
         }
      } catch (Exception varMusic) {
         Gdx.app.log("AoC", "MUSIC AUTO: EXCEPTION " + varMusic);
      }

      new InitGame();
      ShaderProgram.pedantic = false;
      String defaultVertex = Gdx.files.internal("game/shader/default_vertex.glsl").readString();
      String flagFragment = Gdx.files.internal("game/shader/flag_fragment.glsl").readString();
      String nextPlayerTurnVertex = Gdx.files.internal("game/shader/nextPlayerTurn_vertex.glsl").readString();
      shaderAlpha = new ShaderProgram(
         "attribute vec4 a_position;attribute vec4 a_color;attribute vec2 a_texCoord0;uniform mat4 u_projTrans;varying vec4 vColor;varying vec2 vTexCoord;void main() {\tvColor = a_color;\tvTexCoord = a_texCoord0;\tgl_Position =  u_projTrans * a_position;}",
         flagFragment
      );
      shaderAlpha.begin();
      shaderAlpha.setUniformi("u_texture1", 1);
      shaderAlpha.setUniformi("u_mask", 2);
      shaderAlpha.end();
      this.vertexShader = "attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n";
      this.fragmentShader = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform sampler2D u_texture2;\nuniform float u_maskScale;\nuniform float u_useMask;\nuniform vec2 u_maskOffset;\nvoid main()                                  \n{                                            \n vec2 newCoords = -1.0 * (u_maskScale - 1.0)/2.0 + (u_maskScale * v_texCoords) + u_maskOffset;\n vec4 mask = vec4(1.0, 1.0, 1.0, 1.0); \nif(u_useMask > 0.5) \n\tmask = texture2D(u_texture2, v_texCoords);\n vec4 color = v_color * texture2D(u_texture, newCoords);\n  gl_FragColor = vec4(color.rgb, color.a * mask.r);\n}";
      shaderAlpha2 = new ShaderProgram(this.vertexShader, this.fragmentShader);
      shaderAlpha2.begin();
      shaderAlpha2.setUniformi("u_texture", 0);
      shaderAlpha2.setUniformi("u_texture2", 1);
      shaderAlpha2.setUniformf("u_useMask", 1.0F);
      shaderAlpha2.setUniformf("u_maskScale", 20.0F);
      shaderAlpha2.setUniformf("u_maskOffset", 0.0F, 0.0F);
      String defaultFragment = Gdx.files.internal("game/shader/default_fragment.glsl").readString();
      String blackWhiteFragment = Gdx.files.internal("game/shader/blackWhite_fragment.glsl").readString();
      String nextPlayerTurnFragment = Gdx.files.internal("game/shader/nextPlayerTurn_fragment.glsl").readString();
      defaultShader = new ShaderProgram(defaultVertex, defaultFragment);
      blackWhiteShader = new ShaderProgram(defaultVertex, blackWhiteFragment);
      nextPlayerTurnShader = new ShaderProgram(nextPlayerTurnVertex, nextPlayerTurnFragment);
      long time = System.currentTimeMillis();
      loadCursor(true);
      Gdx.app.log("AoC", "LOAD TIME: " + (System.currentTimeMillis() - time));
      if (CFG.isDesktop()) {
         Plusic.created();
      }
   }

   public static final void loadCursor(boolean inInit) {
      if (CFG.settingsManager.loadCursor) {
         try {
            Pixmap pixmap = new Pixmap(Gdx.files.internal("UI/icons/cursor.png"));
            Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
            Gdx.graphics.setCursor(cursor);
            pixmap.dispose();
         } catch (GdxRuntimeException var3) {
         }
      } else if (!inInit) {
         Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
      }
   }

   public static final void updateArmyFontSize() {
      if (CFG.settingsManager.FONT_ARMY_SIZE < 0) {
         CFG.settingsManager.FONT_ARMY_SIZE = !CFG.XXXHDPI && !CFG.XXXXHDPI && !CFG.XXHDPI ? 16 : 18;
      }
   }

   public void update() {
      this.countFPS();

      try {
         CFG.game.update();
      } catch (NullPointerException var10) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var10);
         }
      } catch (IndexOutOfBoundsException var11) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var11);
         }
      } catch (StackOverflowError var12) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var12);
         }
      } catch (ArithmeticException var13) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var13);
         }
      }

      try {
         CFG.map.update();
      } catch (NullPointerException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      } catch (IndexOutOfBoundsException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      } catch (StackOverflowError var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      } catch (ArithmeticException var9) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var9);
         }
      }

      try {
         CFG.menuManager.update();
      } catch (NullPointerException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (StackOverflowError var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (ArithmeticException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }
   }

   @Override
   public void render() {
      this.renderStart = System.currentTimeMillis();

      if (WorldNews.PENDING
         && !CFG.SPECTATOR_MODE
         && CFG.menuManager.getInGameView()
         && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS
         && !CFG.menuManager.getInGameView_Options()) {
         WorldNews.PENDING = false;

         try {
            int tNewsEvID = WorldNews.registerRuntimeEvent(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            Menu_InGame_Event.EVENT_ID = tNewsEvID;
            CFG.menuManager.rebuildInGame_Event();
            Gdx.app.log("AoC", "WORLDNEWS: shown as event #" + tNewsEvID);
         } catch (Exception varWN) {
            CFG.exceptionStack(varWN);
         }
      }

      try {
         this.update();
         this.updateMoveMap();
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      } catch (NullPointerException var7) {
         CFG.exceptionStack(var7);
      } catch (ArithmeticException var8) {
         CFG.exceptionStack(var8);
      } catch (StackOverflowError var9) {
         CFG.exceptionStack(var9);
      } catch (IllegalArgumentException var10) {
         CFG.exceptionStack(var10);
      }

      if (!CFG.RENDER && !CFG.settingsManager.CONTINUOUS_RENDERING) {
         try {
            Thread.sleep((long)(1000.0F / FPS_LIMIT - (float)(System.currentTimeMillis() - this.renderStart)));
         } catch (InterruptedException var11) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var11);
            }
         } catch (IllegalArgumentException var12) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var12);
            }
         } catch (NullPointerException var13) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var13);
            }
         }
      } else {
         try {
            if (CFG.RENDER3) {
               CFG.RENDER3 = false;
            } else if (CFG.RENDER2) {
               CFG.RENDER2 = false;
            } else {
               CFG.RENDER = false;
            }

            Gdx.gl.glClearColor(CFG.BACKGROUND_COLOR.r, CFG.BACKGROUND_COLOR.g, CFG.BACKGROUND_COLOR.b, CFG.BACKGROUND_COLOR.a);
            Gdx.gl.glClear(16640);
            viewport.setWorldSize(CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
            viewport.apply();
            camera.setToOrtho(true, CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), -CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
            this.oSB.setProjectionMatrix(camera.combined);
            this.oSB.begin();
            this.oSB.setShader(defaultShader);
            Game_Render.draw(this.oSB);
            this.oSB.end();
            camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
            viewport.setWorldSize(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            this.oSB.begin();
            Game_Render.drawWithoutScale(this.oSB);
            this.oSB.end();
            viewport.setWorldSize(CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
            viewport.apply();
            camera.setToOrtho(true, CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), -CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
            this.oSB.setProjectionMatrix(camera.combined);
            this.oSB.begin();
            this.oSB.setShader(defaultShader);
            Game_Render.drawMapDetails(this.oSB);
            this.oSB.end();
            camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
            viewport.setWorldSize(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            this.oSB.begin();
            this.oSB.setColor(Color.WHITE);
            if (!screenShotMode) {
               CFG.menuManager.draw(this.oSB);
               CFG.editorManager.draw(this.oSB);
            }

            if (drawFPS || CFG.settingsManager.SHOW_FPS_COUNTER) {
               try {
                  CFG.fontMain.getData().setScale(0.8F);
                  String tFPSText = "FPS: " + CFG.iNumOfFPS;
                  GlyphLayout tFPSLayout = new GlyphLayout(CFG.fontMain, tFPSText);
                  CFG.drawTextWithShadow(
                     this.oSB,
                     tFPSText,
                     (int)(CFG.GAME_WIDTH - tFPSLayout.width - CFG.PADDING * 2),
                     CFG.PADDING * 2,
                     Color.WHITE
                  );
                  CFG.fontMain.getData().setScale(1.0F);
               } catch (NullPointerException var4) {
               } catch (IllegalStateException var5) {
               }
            }

            this.oSB.setColor(Color.WHITE);
            this.oSB.end();
         } catch (IllegalStateException var14) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var14);
            }

            CFG.setRender_3(true);

            try {
               this.oSB.end();
            } catch (IllegalStateException var3) {
            }
         } catch (NullPointerException var15) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var15);
            }

            CFG.setRender_3(true);
         } catch (IndexOutOfBoundsException var16) {
            CFG.exceptionStack(var16);
            CFG.setRender_3(true);
         } catch (StackOverflowError var17) {
            CFG.exceptionStack(var17);
            CFG.setRender_3(true);
         } catch (ArithmeticException var18) {
            CFG.exceptionStack(var18);
            CFG.setRender_3(true);
         } catch (IllegalArgumentException var19) {
            CFG.exceptionStack(var19);
            CFG.setRender_3(true);
         }
      }

      if (CFG.isDesktop()) {
         this.mod.begin();
         Plusic.render(this.mod);
         this.mod.end();
      }

      this.requestRendering.update();
   }

   public void countFPS() {
      this.iNumOfFPS++;
      if (System.currentTimeMillis() > this.lTimeFPS + 1000L) {
         this.lTimeFPS = System.currentTimeMillis();
         CFG.iNumOfFPS = this.iNumOfFPS;
         this.iNumOfFPS = 0;
      }
   }

   @Override
   public void resize(int width, int height) {
      if (CFG.isAndroid()) {
         if (CFG.LANDSCAPE) {
            viewport.update(width, height, false);
         } else {
            viewport.update(-height, -width, false);
         }
      } else {
         viewport.update(width, height, false);
      }

      CFG.setRender_3(true);
   }

   @Override
   public void dispose() {
      try {
         Gdx.app.log("AoC", "dispose");
         if (CFG.isDesktop()) {
         }

         this.oSB.dispose();
         CFG.fontMain.dispose();
         CFG.fontBorder.dispose();

         for (int i = 0; i < ImageManager.getImagesSize(); i++) {
            ImageManager.getImage(i).getTexture().dispose();
         }

         CFG.map.getMapBG().disposeGameMap();
         CFG.soundsManager.dispose();
      } catch (GdxRuntimeException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }

      Plusic.dispose();
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      if (CFG.isDesktop() && Plusic.touchDown(screenX, screenY, pointer, button)) {
         return true;
      } else {
         try {
            Touch.setMousePosXY(screenX, screenY);
            CFG.setRender_3(true);
            this.touch.actionDown(screenX, screenY, pointer);
            CFG.editorManager.touchDown(screenX, screenY, pointer, button);
         } catch (IndexOutOfBoundsException var6) {
            CFG.exceptionStack(var6);
         } catch (NullPointerException var7) {
            CFG.exceptionStack(var7);
         } catch (StackOverflowError var8) {
            CFG.exceptionStack(var8);
         } catch (ArithmeticException var9) {
            CFG.exceptionStack(var9);
         }

         return true;
      }
   }

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {
      if (CFG.isDesktop() && Plusic.touchDragged(screenX, screenY, pointer)) {
         return true;
      } else {
         try {
            Touch.setMousePosXY(screenX, screenY);
            if (Gdx.input.isTouched(1) && pointer == 0) {
               this.touch.actionMove(Gdx.input.getX(0), Gdx.input.getY(0), Gdx.input.getX(1), Gdx.input.getY(1));
            } else {
               this.touch.actionMove(screenX, screenY, pointer);
            }

            CFG.editorManager.touchDragged(screenX, screenY, pointer);
         } catch (IndexOutOfBoundsException var5) {
            CFG.exceptionStack(var5);
         } catch (NullPointerException var6) {
            CFG.exceptionStack(var6);
         } catch (StackOverflowError var7) {
            CFG.exceptionStack(var7);
         } catch (ArithmeticException var8) {
            CFG.exceptionStack(var8);
         }

         return true;
      }
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      if (CFG.isDesktop() && Plusic.touchUp(screenX, screenY, pointer, button)) {
         return true;
      } else {
         try {
            Touch.setMousePosXY(screenX, screenY);
            CFG.setRender_3(true);

            try {
               if (CFG.isDesktop()) {
                  if (CFG.menuManager.getInGameView() && CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                     if (button == 1
                        && !CFG.SPECTATOR_MODE
                        && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS
                        && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                           >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
                        && CFG.game.getActiveProvinceID() >= 0
                        && CFG.gameAction.controlsArmyInProvince(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        && !CFG.menuManager.getVisible_InGame_FlagAction()) {
                        CFG.game.setProvinceID_PPM(screenX, screenY);
                     } else {
                        this.touch.actionUp(screenX, screenY, pointer);
                     }
                  } else {
                     this.touch.actionUp(screenX, screenY, pointer);
                  }
               } else {
                  this.touch.actionUp(screenX, screenY, pointer);
               }
            } catch (NullPointerException var6) {
               this.touch.actionUp(screenX, screenY, pointer);
               if (CFG.LOGS) {
                  CFG.exceptionStack(var6);
               }
            } catch (IndexOutOfBoundsException var7) {
               this.touch.actionUp(screenX, screenY, pointer);
               if (CFG.LOGS) {
                  CFG.exceptionStack(var7);
               }
            }

            CFG.editorManager.touchUp(screenX, screenY, pointer, button);
         } catch (IndexOutOfBoundsException var8) {
            CFG.exceptionStack(var8);
         } catch (NullPointerException var9) {
            CFG.exceptionStack(var9);
         } catch (StackOverflowError var10) {
            CFG.exceptionStack(var10);
         } catch (ArithmeticException var11) {
            CFG.exceptionStack(var11);
         }

         return true;
      }
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
      try {
         Touch.setMousePosXY(screenX, screenY);
         if (CFG.isDesktop()) {
            if (screenX < CFG.PADDING) {
               if (!this.MAP_MOVE_LEFT) {
                  this.MAP_MOVE_LEFT = true;
                  this.MAP_MOVE_RIGHT = false;
                  this.lScrollTime_MAP = System.currentTimeMillis();
                  this.iScroll_MAP = 15.0F;
               }
            } else {
               this.MAP_MOVE_LEFT = false;
            }

            if (screenX > CFG.GAME_WIDTH - CFG.PADDING) {
               if (!this.MAP_MOVE_RIGHT) {
                  this.MAP_MOVE_RIGHT = true;
                  this.MAP_MOVE_LEFT = false;
                  this.lScrollTime_MAP = System.currentTimeMillis();
                  this.iScroll_MAP = 15.0F;
               }
            } else {
               this.MAP_MOVE_RIGHT = false;
            }

            if (screenY < CFG.PADDING) {
               if (!this.MAP_MOVE_TOP) {
                  this.MAP_MOVE_TOP = true;
                  this.MAP_MOVE_BOT = false;
                  this.lScrollTime_MAPY = System.currentTimeMillis();
                  this.iScroll_MAPY = 15.0F;
               }
            } else {
               this.MAP_MOVE_TOP = false;
            }

            if (screenY > CFG.GAME_HEIGHT - CFG.PADDING) {
               if (!this.MAP_MOVE_BOT) {
                  this.MAP_MOVE_BOT = true;
                  this.MAP_MOVE_TOP = false;
                  this.lScrollTime_MAPY = System.currentTimeMillis();
                  this.iScroll_MAPY = 15.0F;
               }
            } else {
               this.MAP_MOVE_BOT = false;
            }
         }

         this.touch.actionMove_Hover(screenX, screenY);
         return true;
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
      } catch (StackOverflowError var6) {
         CFG.exceptionStack(var6);
      } catch (ArithmeticException var7) {
         CFG.exceptionStack(var7);
      }

      return true;
   }

   public final void updateMoveMap() {
      try {
         if (this.MAP_MOVE_LEFT) {
            this.updateScroll_Map();
            CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() + (int)this.iScroll_MAP);
         } else if (this.MAP_MOVE_RIGHT) {
            this.updateScroll_Map();
            CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() - (int)this.iScroll_MAP);
         }

         if (this.MAP_MOVE_TOP) {
            this.updateScroll_MapY();
            CFG.map.getMapCoordinates().setNewPosY(CFG.map.getMapCoordinates().getPosY() + (int)this.iScroll_MAPY);
         } else if (this.MAP_MOVE_BOT) {
            this.updateScroll_MapY();
            CFG.map.getMapCoordinates().setNewPosY(CFG.map.getMapCoordinates().getPosY() - (int)this.iScroll_MAPY);
         }
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (ArithmeticException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   public final void updateScroll_Map() {
      if (this.lScrollTime_MAP + 150L < System.currentTimeMillis()) {
         this.lScrollTime_MAP = System.currentTimeMillis();
         this.iScroll_MAP = this.iScroll_MAP + this.iScroll_MAP * 0.475F;
         float f = CFG.map.getMapScale().getCurrentScale() < 1.0F ? 1.0F + (1.0F - CFG.map.getMapScale().getCurrentScale()) : 1.0F;
         if (this.iScroll_MAP > 35.0F * f) {
            this.iScroll_MAP = 35.0F * (CFG.map.getMapScale().getCurrentScale() < 1.0F ? 1.0F + (1.0F - CFG.map.getMapScale().getCurrentScale()) : 1.0F);
         }
      }
   }

   public final void updateScroll_MapY() {
      if (this.lScrollTime_MAPY + 150L < System.currentTimeMillis()) {
         this.lScrollTime_MAPY = System.currentTimeMillis();
         this.iScroll_MAPY = this.iScroll_MAPY + this.iScroll_MAPY * 0.475F;
         float f = CFG.map.getMapScale().getCurrentScale() < 1.0F ? 1.0F + (1.0F - CFG.map.getMapScale().getCurrentScale()) : 1.0F;
         if (this.iScroll_MAPY > 35.0F * f) {
            this.iScroll_MAPY = 35.0F * (CFG.map.getMapScale().getCurrentScale() < 1.0F ? 1.0F + (1.0F - CFG.map.getMapScale().getCurrentScale()) : 1.0F);
         }
      }
   }

   @Override
   public boolean keyDown(int keycode) {
      boolean bool = false;
      if (CFG.isDesktop() && Plusic.keyDown(keycode)) {
         return true;
      } else {
         try {
            CFG.setRender_3(true);
            if (!CFG.menuManager.getKeyboard().getVisible()) {
               if (CFG.editorManager.keyDown(keycode)) {
                  return true;
               }

               if (keycode == 21) {
                  this.MAP_MOVE_LEFT = true;
                  this.MAP_MOVE_RIGHT = false;
                  this.lScrollTime_MAP = System.currentTimeMillis();
                  this.iScroll_MAP = 15.0F;
               }

               if (keycode == 22) {
                  this.MAP_MOVE_RIGHT = true;
                  this.MAP_MOVE_LEFT = false;
                  this.lScrollTime_MAP = System.currentTimeMillis();
                  this.iScroll_MAP = 15.0F;
               }

               if (keycode == 19) {
                  this.MAP_MOVE_TOP = true;
                  this.MAP_MOVE_BOT = false;
                  this.lScrollTime_MAPY = System.currentTimeMillis();
                  this.iScroll_MAPY = 15.0F;
               }

               if (keycode == 20) {
                  this.MAP_MOVE_BOT = true;
                  this.MAP_MOVE_TOP = false;
                  this.lScrollTime_MAPY = System.currentTimeMillis();
                  this.iScroll_MAPY = 15.0F;
               }

               if (keycode == 49) {
                  if (!screenShotMode) {
                     bool = true;
                  }

                  screenShotMode = bool;
               }
            }
         } catch (IndexOutOfBoundsException var4) {
            CFG.exceptionStack(var4);
         } catch (NullPointerException var5) {
            CFG.exceptionStack(var5);
         } catch (StackOverflowError var6) {
            CFG.exceptionStack(var6);
         } catch (ArithmeticException var7) {
            CFG.exceptionStack(var7);
         }

         return true;
      }
   }

   public final void typeNumber(int iNum) {
      if (System.currentTimeMillis() - 625L > TYPE_NUMER_TIME) {
         TYPE_NUMBER = iNum;
      } else {
         TYPE_NUMBER *= 10;
         TYPE_NUMBER += iNum;
      }

      TYPE_NUMER_TIME = System.currentTimeMillis();
   }

   public static final void resetTypeNumber() {
      TYPE_NUMER_TIME = 0L;
      TYPE_NUMBER = 0;
   }

   @Override
   public boolean keyUp(int keycode) {
      CFG.setRender_3(true);
      if (CFG.isDesktop() && Plusic.keyUp(keycode)) {
         return true;
      } else {
         try {
            if (CFG.menuManager.getKeyboard().getVisible()) {
               if (keycode != 66) {
                  return false;
               } else {
                  CFG.keyboardSave.action();
                  CFG.menuManager.getKeyboard().onMenuPressed();
                  CFG.menuManager.getKeyboard().setVisible(false);
                  Keyboard.activeColor_RGB_ID = -1;
                  return false;
               }
            } else if (CFG.editorManager.keyUp(keycode)) {
               return true;
            } else {
               if (keycode == 21) {
                  this.MAP_MOVE_LEFT = false;
               }

               if (keycode == 22) {
                  this.MAP_MOVE_RIGHT = false;
               }

               if (keycode == 19) {
                  this.MAP_MOVE_TOP = false;
               }

               if (keycode == 20) {
                  this.MAP_MOVE_BOT = false;
               }

               if (!CFG.isDesktop()) {
                  return false;
               } else if (CFG.menuManager.getDialogMenu().getVisible()) {
                  if (keycode == 66 || keycode == 62) {
                     CFG.menuManager.getDialogMenu().getMenuElement(1).setClickable(false);
                     CFG.menuManager.getDialogMenu().getMenuElement(2).setClickable(false);
                     CFG.dialog_True();
                     CFG.menuManager.getDialogMenu().onBackPressed();
                     return false;
                  } else if (keycode != 131 && keycode != 67) {
                     return false;
                  } else {
                     CFG.menuManager.getDialogMenu().getMenuElement(1).setClickable(false);
                     CFG.menuManager.getDialogMenu().getMenuElement(2).setClickable(false);
                     CFG.dialog_False();
                     CFG.menuManager.getDialogMenu().onBackPressed();
                     return false;
                  }
               } else if (!CFG.menuManager.getInGameView()) {
                  if (CFG.menuManager.getInNextPlayerTurn()) {
                     if (keycode != 62) {
                        return false;
                     } else {
                        Menu_NextPlayerTurn.clickBack();
                        return false;
                     }
                  } else if (keycode == 67) {
                     CFG.menuManager.onBackPressed();
                     return false;
                  } else if (!CFG.menuManager.getInGame_Timeline() && !CFG.menuManager.getInVictory()) {
                     if (CFG.menuManager.getInGame_CreateAVassal()) {
                        CFG.menuManager.setViewID(Menu.eINGAME);
                        return false;
                     } else if (CFG.menuManager.getInCreateNewGame()) {
                        if (keycode == 244) {
                           Menu_CreateNewGame.clickOptions();
                           return false;
                        } else if (keycode != 61) {
                           return false;
                        } else {
                           Menu_CreateNewGame_Top.clickChooseScenario();
                           return false;
                        }
                     } else if (!CFG.menuManager.getInChooseScenario()) {
                        return false;
                     } else if (keycode == 66 || keycode == 62) {
                        Menu_ChooseScenario_Title.clickLoadScenario();
                        return false;
                     } else if (keycode == 61) {
                        CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
                        return false;
                     } else if (keycode == 20 || keycode == 22) {
                        CFG.game.getGameScenarios();
                        if (++Menu_ChooseScenario_Title.iPreviewScenarioID >= Game_Scenarios.SCENARIOS_SIZE - 1) {
                           Menu_ChooseScenario_Title.iPreviewScenarioID = 0;
                        }

                        Menu_ChooseScenario_Title.loadPreview();
                        return false;
                     } else if (keycode != 19 && keycode != 21) {
                        return false;
                     } else {
                        if (--Menu_ChooseScenario_Title.iPreviewScenarioID < 0) {
                           CFG.game.getGameScenarios();
                           Menu_ChooseScenario_Title.iPreviewScenarioID = Game_Scenarios.SCENARIOS_SIZE - 1;
                        }

                        Menu_ChooseScenario_Title.loadPreview();
                        return false;
                     }
                  } else if (keycode == 69) {
                     CFG.timelapseManager.updateSpeed(-1);
                     return false;
                  } else if (keycode == 81) {
                     CFG.timelapseManager.updateSpeed(1);
                     return false;
                  } else if (keycode != 66) {
                     return false;
                  } else {
                     CFG.timelapseManager.pauseUnpause();
                     return false;
                  }
               } else if (keycode == 3) {
                  try {
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                     if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                        CFG.game.disableDrawCivilizationRegions_Active();
                        CFG.game.enableDrawCivilizationRegions_ActiveProvince();
                     }

                     if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                        CFG.menuManager.setVisible_InGame_FlagAction(false);
                     }

                     if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName().length() <= 0
                        )
                      {
                        return false;
                     } else {
                        CFG.toast
                           .setInView(
                              CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName(),
                              CFG.COLOR_TEXT_NUM_OF_PROVINCES
                           );
                        return false;
                     }
                  } catch (IndexOutOfBoundsException var5) {
                     if (!CFG.LOGS) {
                        return false;
                     } else {
                        CFG.exceptionStack(var5);
                        return false;
                     }
                  }
               } else if (keycode == 131) {
                  if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                     Menu_InGame.clickFlagAction();
                     return false;
                  } else if (CFG.regroupArmyMode) {
                     VoiceManager.playCancel();
                  CFG.game.resetRegroupArmyData();
                     CFG.game.checkProvinceActionMenu();
                     return false;
                  } else if (CFG.chooseProvinceMode || CFG.chosenProvinceID >= 0) {
                     CFG.game.resetChooseProvinceData();
                     CFG.game.checkProvinceActionMenu();
                     return false;
                  } else if (CFG.menuManager.getInGameView_Options()) {
                     Menu_InGame_Options.clickBack();
                     return false;
                  } else if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                     CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
                     CFG.game.checkProvinceActionMenu();
                     return false;
                  } else if (CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()) {
                     CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
                     CFG.game.checkProvinceActionMenu();
                     return false;
                  } else if (CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                     CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
                     CFG.game.checkProvinceActionMenu();
                     return false;
                  } else {
                     Menu_InGame_FlagAction.clickOptions();
                     return false;
                  }
               } else if (keycode == 31) {
                  Game_Render.DISABLE_CITIES = !Game_Render.DISABLE_CITIES;
                  CFG.toast
                     .setInView(
                        "C, " + CFG.langManager.get("Cities"),
                        !Game_Render.DISABLE_CITIES ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     );
                  CFG.toast.setTimeInView(4500);
                  return false;
               } else if (keycode == 50) {
                  Game_Render.DISABLE_CIVS_NAMES = !Game_Render.DISABLE_CIVS_NAMES;
                  CFG.toast
                     .setInView(
                        "V, " + CFG.langManager.get("CivilizationsNames"),
                        !Game_Render.DISABLE_CIVS_NAMES ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     );
                  CFG.toast.setTimeInView(4500);
                  return false;
               } else if (keycode == 69) {
                  RTS.updateSpeed(-1);
                  return false;
               } else if (keycode == 81) {
                  RTS.updateSpeed(1);
                  return false;
               } else if (keycode == 66) {
                  if (!CFG.menuManager.getInGameView_Options()
                     && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS
                     && (
                        CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()
                           || CFG.menuManager.getInGame_ProvinceRecruit_Visible()
                           || CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()
                           || CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()
                           || CFG.menuManager.getInGame_ProvinceDisband_Visible()
                     )) {
                     if (CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()) {
                        CFG.menuManager.getInGame_ProvinceMoveUnits_Confrim();
                     }

                     if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRecruit_Confrim();
                     }

                     if (CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRecruitInstantly_Confrim();
                     }

                     if (CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRegroupArmy_ConfirmMove();
                     }

                     if (!CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                        return false;
                     } else {
                        CFG.menuManager.getInGame_ProvinceDisband_Confrm();
                        return false;
                     }
                  } else {
                     RTS.pauseUnpause();
                     return false;
                  }
               } else if (CFG.menuManager.getInGameView_Options()) {
                  return false;
               } else {
                  if (keycode == 62) {
                     if (CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRegroupArmy_ConfirmMove();
                        CFG.soundsManager.playSound(SoundsManager.SOUND_MOVE_REGROUP);
                        return true;
                     }

                     if (CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                        CFG.menuManager.getInGame_ProvinceDisband_Confrm();
                        CFG.soundsManager.playSound(SoundsManager.SOUND_CLICK2);
                        return true;
                     }

                     if (CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRecruitInstantly_Confrim();
                        CFG.soundsManager.playSound(SoundsManager.SOUND_RECRUIT);
                        return true;
                     }

                     if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                        CFG.menuManager.getInGame_ProvinceRecruit_Confrim();
                        CFG.soundsManager.playSound(SoundsManager.SOUND_RECRUIT);
                        return true;
                     }

                     if (CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()) {
                        CFG.menuManager.getInGame_ProvinceMoveUnits_Confrim();
                        CFG.soundsManager.playSound(SoundsManager.SOUND_MOVE_ARMY);
                        return true;
                     }

                     if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                        RTS.PAUSE = true;
                        RTS.resetTime();
                     }

                     try {
                        if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getClickable()) {
                           Menu_InGame_ProvinceInfo.clickEndTurn();
                        }
                     } catch (NullPointerException var3) {
                        CFG.exceptionStack(var3);
                     } catch (IndexOutOfBoundsException var4) {
                        CFG.exceptionStack(var4);
                     }
                  }

                  if (CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) {
                     return false;
                  } else {
                     if (CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()
                        || CFG.menuManager.getInGame_ProvinceRecruit_Visible()
                        || CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()
                        || CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()
                        || CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                        if (keycode == 7 || keycode == 144) {
                           this.typeNumber(0);
                        } else if (keycode == 8 || keycode == 145) {
                           this.typeNumber(1);
                        } else if (keycode == 9 || keycode == 146) {
                           this.typeNumber(2);
                        } else if (keycode == 10 || keycode == 147) {
                           this.typeNumber(3);
                        } else if (keycode == 11 || keycode == 148) {
                           this.typeNumber(4);
                        } else if (keycode == 12 || keycode == 149) {
                           this.typeNumber(5);
                        } else if (keycode == 13 || keycode == 150) {
                           this.typeNumber(6);
                        } else if (keycode == 14 || keycode == 151) {
                           this.typeNumber(7);
                        } else if (keycode == 15 || keycode == 152) {
                           this.typeNumber(8);
                        } else if (keycode == 16 || keycode == 153) {
                           this.typeNumber(9);
                        }

                        if (CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()) {
                           CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setCurrent(TYPE_NUMBER);
                           CFG.menuManager.updateInGame_ActionInfo_Move();
                        }

                        if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                           CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(TYPE_NUMBER);
                           CFG.menuManager.updateInGame_ActionInfo_Recruit();
                        }

                        if (CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()) {
                           CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(TYPE_NUMBER);
                           CFG.menuManager.updateInGame_ActionInfo_RecruitInstantly();
                        }

                        if (CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()) {
                           CFG.menuManager.getInGame_RegroupArmy_Slider().setCurrent(TYPE_NUMBER);
                           CFG.menuManager.updateInGame_ActionInfo_Regroup();
                        }

                        if (CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                           CFG.menuManager.getInGame_ProvinceDisband_Slider().setCurrent(TYPE_NUMBER);
                           CFG.menuManager.updateInGame_ActionInfo_Disband();
                        }
                     }

                  if (keycode == 68) {
                     CFG.menuManager.setVisible_InGame_FlagAction_Console(!CFG.menuManager.getVisible_InGame_FlagAction_Console());
                  }

                  if (keycode == 41) {
                     AI_Style.FullAssimilateProvinces(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     CFG.toast.setInView("M, " + CFG.langManager.get("FullAssimilation"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                     CFG.toast.setTimeInView(4500);
                     CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  }

                  if (keycode == 42) {
                     AI_Style.AssimilateAllPeopleProvinces(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     CFG.toast.setInView("N, " + CFG.langManager.get("AssimilateAllPeople"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                     CFG.toast.setTimeInView(4500);
                     CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  }

                  if (keycode == 35) {
                     CFG.menuManager.setVisible_InGame_Generals(!CFG.menuManager.isInGame_GeneralsVisible());
                  }

                  if (keycode == 43) {
                     AI_Assistant.PARTISAN_MAP_ON = !AI_Assistant.PARTISAN_MAP_ON;
                     CFG.toast.setInView(
                        CFG.langManager.get("Partisans_Mode") + ": "
                           + CFG.langManager.get(AI_Assistant.PARTISAN_MAP_ON ? "AI_Set_On" : "AI_Set_Off"),
                        AI_Assistant.PARTISAN_MAP_ON ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     );
                     CFG.toast.setTimeInView(2500);
                  }

                  if (keycode == 39) {
                     if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_REVOLUTION_MODE) {
                        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
                     } else {
                        CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                        CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_REVOLUTION_MODE);
                     }

                     Game_Render_Province.updateDrawProvinces();
                  }

                  if (keycode == 40) {
                     if (AI_Assistant.PARTISAN_HOTSPOTS.isEmpty()) {
                        CFG.toast.setInView(CFG.langManager.get("Partisans_None"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                        CFG.toast.setTimeInView(2500);
                     } else {
                        if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_REVOLUTION_MODE) {
                           CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                           CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_REVOLUTION_MODE);
                           AI_Assistant.PARTISAN_VIEW_INDEX = 0;
                        } else {
                           AI_Assistant.PARTISAN_VIEW_INDEX =
                              (AI_Assistant.PARTISAN_VIEW_INDEX + 1) % AI_Assistant.PARTISAN_HOTSPOTS.size();
                        }

                        int tHotspot = AI_Assistant.PARTISAN_HOTSPOTS.get(
                           Math.min(AI_Assistant.PARTISAN_VIEW_INDEX, AI_Assistant.PARTISAN_HOTSPOTS.size() - 1)
                        );
                        CFG.map.getMapCoordinates().centerToProvinceID(tHotspot);
                        CFG.toast.setInView(
                           CFG.langManager.get("Partisans_Hotspot") + " "
                              + (AI_Assistant.PARTISAN_VIEW_INDEX + 1)
                              + "/"
                              + AI_Assistant.PARTISAN_HOTSPOTS.size(),
                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        );
                        CFG.toast.setTimeInView(2000);
                     }
                  }

                     if (keycode == 244) {
                        Menu_InGame.clickFlagAction();
                     } else if (keycode != 245 && keycode != 61) {
                        if (keycode == 246) {
                           CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
                        } else if (keycode == 247) {
                           CFG.menuManager.setVisible_InGame_MapModes(!CFG.menuManager.getInGame_MapModes().getVisible());
                           if (CFG.menuManager.getInGame_MapModes().getPosX() < 0) {
                              CFG.menuManager
                                 .getInGame_MapModes()
                                 .setPosX_Force(
                                    CFG.menuManager.getInGame().getMenuElement(9).getPosX()
                                       + CFG.menuManager.getInGame().getMenuElement(9).getWidth() / 2
                                       - CFG.menuManager.getInGame_MapModes().getWidth() / 2
                                 );
                              CFG.menuManager
                                 .getInGame_MapModes()
                                 .setPosY(
                                    CFG.menuManager.getInGame_MapModes().getTitle().getHeight()
                                       + CFG.menuManager.getInGame().getMenuElement(9).getPosY()
                                       + CFG.menuManager.getInGame().getMenuElement(9).getHeight()
                                       + CFG.PADDING
                                 );
                              if (CFG.menuManager.getInGame_MapModes().getPosX() + CFG.menuManager.getInGame_MapModes().getWidth()
                                 > CFG.GAME_WIDTH - CFG.PADDING) {
                                 CFG.menuManager
                                    .getInGame_MapModes()
                                    .setPosX_Force(CFG.GAME_WIDTH - CFG.PADDING - CFG.menuManager.getInGame_MapModes().getWidth());
                              }
                           }
                        } else if (keycode == 248) {
                           if (CFG.menuManager.getVisible_Menu_InGame_Outliner()) {
                              CFG.menuManager.setVisible_Menu_InGame_Outliner(false);
                           } else {
                              Menu_InGame_FlagAction.clickStats();
                           }
                        } else if (keycode == 249) {
                           if (CFG.menuManager.getVisibleInGame_Wars()) {
                              CFG.menuManager.setVisibleInGame_Wars(false);
                           } else {
                              CFG.menuManager.rebuildInGame_Wars();
                           }
                        } else if (keycode == 250) {
                           if (CFG.menuManager.getVisibleInGame_MilitaryAlliances()) {
                              CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
                           } else {
                              CFG.menuManager.rebuildInGame_MilitaryAlliances();
                           }
                        } else if (keycode == 251) {
                           if (CFG.menuManager.getVisibleInGame_History()) {
                              CFG.menuManager.setVisibleInGame_History(false);
                           } else {
                              CFG.menuManager.rebuildInGame_History();
                           }
                        } else if (keycode == 252) {
                           if (CFG.menuManager.getVisibleInGame_Rank()) {
                              CFG.menuManager.setVisibleInGame_Rank(false);
                           } else {
                              CFG.menuManager.rebuildInGame_Rank();
                           }
                        } else if (keycode == 255) {
                           CFG.menuManager.setVisibleInGame_Playlist(!CFG.menuManager.getVisibleInGame_Playlist());
                        } else if (keycode != 8) {
                           if (keycode == 45) {
                              if (CFG.chooseProvinceMode) {
                                                                  VoiceManager.playCancel();
                                 CFG.game.resetChooseProvinceData();
                                 CFG.game.checkProvinceActionMenu();
                                 return true;
                              }
                           } else if (keycode == 51) {
                              if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                                 CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
                                 CFG.game.checkProvinceActionMenu();
                                 return true;
                              }
                           } else if (keycode == 33) {
                              if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
                                 CFG.menuManager.setVisible_InGame_ProvinceBuild(false, false);
                                 return true;
                              }
                           } else if (keycode == 46) {
                              if (CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                                 CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
                                 CFG.game.checkProvinceActionMenu();
                                 return true;
                              }
                           } else if (keycode == 48 && CFG.regroupArmyMode) {
                                                            VoiceManager.playCancel();
                              CFG.game.resetRegroupArmyData();
                              CFG.game.checkProvinceActionMenu();
                              return true;
                           }
                        }
                     } else {
                        if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                           Menu_InGame.clickFlagAction();
                        }

                        CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                     }

                     if (!CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                        return false;
                     } else if (keycode == 45) {
                        if (CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) {
                           return false;
                        } else if (!CFG.chooseProvinceMode) {
                           Menu_InGame_ProvinceAction.clickMove();
                           return false;
                        } else {
                           CFG.game.resetChooseProvinceData();
                           CFG.game.checkProvinceActionMenu();
                           return false;
                        }
                     } else if (keycode == 51) {
                        if (!Menu_InGame_ProvinceAction.canRecruit()) {
                           return false;
                        } else {
                           Menu_InGame_ProvinceAction.clickRecruit();
                           return false;
                        }
                     } else if (keycode == 33) {
                        if (!Menu_InGame_ProvinceAction.canRecruit()) {
                           return false;
                        } else {
                           Menu_InGame_ProvinceAction.clickBuild();
                           return false;
                        }
                     } else if (keycode == 46) {
                        Menu_InGame_ProvinceAction.clickDisband();
                        return false;
                     } else if (keycode != 48) {
                        return false;
                     } else {
                        Menu_InGame_ProvinceAction.clickMoveTo();
                        return false;
                     }
                  }
               }
            }
         } catch (IndexOutOfBoundsException var6) {
            CFG.exceptionStack(var6);
            return false;
         } catch (NullPointerException var7) {
            CFG.exceptionStack(var7);
            return false;
         } catch (StackOverflowError var8) {
            CFG.exceptionStack(var8);
            return false;
         } catch (ArithmeticException var9) {
            CFG.exceptionStack(var9);
            return false;
         }
      }
   }

   @Override
   public boolean keyTyped(char character) {
      CFG.setRender_3(true);
      Gdx.app.log("AoC", "" + character + " character: " + character);

      try {
         if (CFG.menuManager.getKeyboard().getVisible() && character > 0) {
            if (character == 18 || character == '\b') {
               CFG.keyboardDelete.action();
               CFG.menuManager.getKeyboard().onMenuPressed();
            } else if (character != '\r' && character != ';' && character != '<') {
               CFG.keyboardWrite.action("" + character);
               CFG.menuManager.getKeyboard().onMenuPressed();
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      } catch (NullPointerException var8) {
         CFG.exceptionStack(var8);
      } catch (StackOverflowError var9) {
         CFG.exceptionStack(var9);
      } catch (ArithmeticException var10) {
         CFG.exceptionStack(var10);
      }

      try {
         CFG.soundsManager.playSound(SoundsManager.SOUND_CLICK, SoundsManager.PERC_VOLUME_KEYBOARD);
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      } catch (NullPointerException var4) {
         CFG.exceptionStack(var4);
      } catch (StackOverflowError var5) {
         CFG.exceptionStack(var5);
      } catch (ArithmeticException var6) {
         CFG.exceptionStack(var6);
      }

      return false;
   }

   public float getScrolled_ScaleUpdate() {
      if (CFG.map.getMapScale().getCurrentScale() <= 1.0F) {
         if (CFG.map.getMapScale().getCurrentScale() >= 0.65F) {
            return 0.05F;
         } else {
            return CFG.map.getMapScale().getCurrentScale() >= 0.4F ? 0.02F : 0.01F;
         }
      } else {
         return 0.1F;
      }
   }

   @Override
   public boolean scrolled(int amount) {
      try {
         if (CFG.menuManager.getIsScrollableY_MenuHovered()) {
            this.updateScroll();
            CFG.menuManager.scrollHoveredMenu_Y(amount == 1 ? -this.iScroll : this.iScroll);
         } else if (CFG.menuManager.getIsScrollableX_MenuHovered()) {
            this.updateScroll();
            CFG.menuManager.scrollHoveredMenu_X(amount == 1 ? -this.iScroll : this.iScroll);
         } else if (CFG.menuManager.getIsScrollable_Hovered_MenuElement()) {
            this.updateScroll();
            CFG.menuManager.scrollHoveredMenuElement(amount == 1 ? -this.iScroll : this.iScroll);
         } else if (amount == 1) {
            CFG.map.getMapScale().setNewCurrentScaleByButton2(-this.getScrolled_ScaleUpdate());
         } else if (amount == -1) {
            CFG.map.getMapScale().setNewCurrentScaleByButton2(this.getScrolled_ScaleUpdate());
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      } catch (NullPointerException var8) {
         CFG.exceptionStack(var8);
      } catch (StackOverflowError var9) {
         CFG.exceptionStack(var9);
      } catch (ArithmeticException var10) {
         CFG.exceptionStack(var10);
      }

      try {
         CFG.soundsManager.playSound(SoundsManager.SOUND_CLICK, SoundsManager.PERC_VOLUME_KEYBOARD);
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      } catch (NullPointerException var4) {
         CFG.exceptionStack(var4);
      } catch (StackOverflowError var5) {
         CFG.exceptionStack(var5);
      } catch (ArithmeticException var6) {
         CFG.exceptionStack(var6);
      }

      return true;
   }

   public final void updateScroll() {
      if (this.lScrollTime + 50L > System.currentTimeMillis()) {
         this.lScrollTime = System.currentTimeMillis();
         this.iScroll = this.iScroll + (int)(this.iScroll * 1.2F);
         if (this.iScroll > 75) {
            this.iScroll = 75;
         }
      } else {
         this.lScrollTime = System.currentTimeMillis();
         this.iScroll = 15;
      }
   }

   @Override
   public void resume() {
      CFG.updateRender(true);
      this.updateRequestRendering(true);
      Gdx.graphics.requestRendering();
      CFG.setRender_3(true);
      super.resume();
   }

   @Override
   public void pause() {
      this.updateRequestRendering(false);
      if (!CFG.menuManager.getInLoadMap() && !CFG.menuManager.getInInitMenu()) {
         CFG.updateRender(false);
      }

      CFG.setRender_3(true);
      super.pause();
   }

   interface RequestRendering {
      void update();
   }
}
