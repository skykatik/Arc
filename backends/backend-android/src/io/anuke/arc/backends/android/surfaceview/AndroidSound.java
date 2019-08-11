package io.anuke.arc.backends.android.surfaceview;

import android.media.AudioManager;
import android.media.SoundPool;
import io.anuke.arc.audio.Sound;
import io.anuke.arc.collection.IntArray;

final class AndroidSound implements Sound{
    final SoundPool soundPool;
    final AudioManager manager;
    final int soundId;
    final IntArray streamIds = new IntArray(8);

    AndroidSound(SoundPool pool, AudioManager manager, int soundId){
        this.soundPool = pool;
        this.manager = manager;
        this.soundId = soundId;
    }

    @Override
    public void dispose(){
        soundPool.unload(soundId);
    }

    @Override
    public void stop(){
        for(int i = 0, n = streamIds.size; i < n; i++)
            soundPool.stop(streamIds.get(i));
    }

    @Override
    public void stop(int soundId){
        soundPool.stop((int)soundId);
    }

    @Override
    public void pause(){
        soundPool.autoPause();
    }

    @Override
    public void pause(int soundId){
        soundPool.pause(soundId);
    }

    @Override
    public void resume(){
        soundPool.autoResume();
    }

    @Override
    public void resume(int soundId){
        soundPool.resume(soundId);
    }

    @Override
    public void setPitch(int soundId, float pitch){
        soundPool.setRate(soundId, pitch);
    }

    @Override
    public void setVolume(int soundId, float volume){
        soundPool.setVolume(soundId, volume, volume);
    }

    @Override
    public void setLooping(int soundId, boolean looping){
        soundPool.setLoop(soundId, looping ? -1 : 0);
    }

    @Override
    public void setPan(int soundId, float pan, float volume){
        float leftVolume = volume;
        float rightVolume = volume;

        if(pan < 0){
            rightVolume *= (1 - Math.abs(pan));
        }else if(pan > 0){
            leftVolume *= (1 - Math.abs(pan));
        }

        soundPool.setVolume(soundId, leftVolume, rightVolume);
    }

    @Override
    public int play(float volume, float pitch, float pan, boolean looping){
        if(streamIds.size == 8) streamIds.pop();
        float leftVolume = volume;
        float rightVolume = volume;
        if(pan < 0){
            rightVolume *= (1 - Math.abs(pan));
        }else if(pan > 0){
            leftVolume *= (1 - Math.abs(pan));
        }
        int streamId = soundPool.play(soundId, leftVolume, rightVolume, 1, looping ? -1 : 0, pitch);
        // standardise error code with other backends
        if(streamId == 0) return -1;
        streamIds.insert(0, streamId);
        return streamId;
    }
}
