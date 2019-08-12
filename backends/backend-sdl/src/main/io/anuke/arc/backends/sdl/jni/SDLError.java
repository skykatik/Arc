package io.anuke.arc.backends.sdl.jni;

public class SDLError extends RuntimeException{

    public SDLError() {
        super(SDL.SDL_GetError());
    }

    public SDLError(String msg){
        super(msg);
    }
}
