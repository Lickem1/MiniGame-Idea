package tk.lickem.minigameidea.manager;

import lombok.Getter;
import tk.lickem.minigameidea.data.player.PlayerData;

import java.util.UUID;
import java.util.WeakHashMap;

public class PlayerDataManager {

    @Getter
    private final WeakHashMap<UUID, PlayerData> playerMaps = new WeakHashMap<>();

    public PlayerData getProfile(UUID uuid) {
        return playerMaps.get(uuid);
    }

    public PlayerData createProfile(UUID uuid) {
        if(playerMaps.containsKey(uuid)) return getProfile(uuid);
        else return playerMaps.put(uuid, new PlayerData(uuid));
    }

    public boolean deleteProfile(UUID uuid) {
        try {
            playerMaps.remove(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
