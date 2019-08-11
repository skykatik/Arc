package sdl;

public class Soloud{

    /*JNI

    #define WITH_SDL2_STATIC

    #include "soloud/soloud_c.h"
    //#include "soloud/soloud.h"

     */

    public static native void test(); /*

        Soloud *soloud = Soloud_create();
        Speech *speech = Speech_create();

        Speech_setText(speech, "oh no oh no oh no oh no oh no");

        Soloud_initEx(soloud, SOLOUD_CLIP_ROUNDOFF | SOLOUD_ENABLE_VISUALIZATION,
                      SOLOUD_AUTO, SOLOUD_AUTO, SOLOUD_AUTO, SOLOUD_AUTO);

        Soloud_setGlobalVolume(soloud, 4);
        Soloud_play(soloud, speech);
    */
}
