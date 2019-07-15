package fr.wydix.hyBot;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import net.dv8tion.jda.client.requests.restaction.pagination.MentionPaginationAction;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Region;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Invite;
import net.dv8tion.jda.core.entities.ListedEmote;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.managers.GuildManager;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.MemberAction;
import net.dv8tion.jda.core.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.core.utils.cache.MemberCacheView;
import net.dv8tion.jda.core.utils.cache.SnowflakeCacheView;
//An object with more Object in usefull to get some informations
public class SGuild implements Guild {
	private String commandSymbol;
	private Guild guild;
	private File saved;
	private String guildID;
	
	private String youtubeChannel;
	private String twitterChannel;
	private String websiteChannel;
	public SGuild(Guild guild, File whatFile) throws IOException {
		super();
		this.setGuild(guild);
		this.setGuildID(guild.getId());
		this.setCommandSymbol(FileUser.read(whatFile, new String[]{"symbol"}));
		this.setSaved(whatFile);
		this.youtubeChannel  = FileUser.read(whatFile, new String[]{"link","Youtube"});
		this.twitterChannel  = FileUser.read(whatFile, new String[]{"link","Twitter"});
		this.websiteChannel  = FileUser.read(whatFile, new String[]{"link","Website"});
		
	}
	public void setSymbol(String newSymbol) throws IOException {
		FileUser.write(this.getSaved(), new String[] {"symbol"}, newSymbol);
		this.commandSymbol  = newSymbol;
	}
	public static SGuild getSGuild(Guild guild) {
		return HyBot.getSavedGuild().stream().filter(x->x.getGuild() == guild).findFirst().get();
	}
	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String newYoutubeChannel) throws IOException {
		FileUser.write(this.getSaved(), new String[] {"link","Youtube"}, newYoutubeChannel);
		this.youtubeChannel  = newYoutubeChannel;
	}

	public String getTwitterChannel() {
		return twitterChannel;
	}

	public void setTwitterChannel(String newTwitterChannel) throws IOException {
		FileUser.write(this.getSaved(), new String[] {"link","Twitter"}, newTwitterChannel);
		this.twitterChannel  = newTwitterChannel;
	}

	public String getWebsiteChannel() {
		return websiteChannel;
	}

	public void setWebsiteChannel(String newWebsiteChannel) throws IOException {
		FileUser.write(this.getSaved(), new String[] {"link","Website"}, newWebsiteChannel);
		this.websiteChannel  = newWebsiteChannel;
	}

	@Override
	public long getIdLong() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public RestAction<EnumSet<Region>> retrieveRegions(boolean includeDeprecated) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberAction addMember(String accessToken, String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIconId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIconUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<String> getFeatures() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSplashId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSplashUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<String> getVanityUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public VoiceChannel getAfkChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TextChannel getSystemChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Member getOwner() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getOwnerIdLong() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Timeout getAfkTimeout() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRegionRaw() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isMember(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Member getSelfMember() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Member getMember(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberCacheView getMemberCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SnowflakeCacheView<Category> getCategoryCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SnowflakeCacheView<TextChannel> getTextChannelCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Channel> getChannels(boolean includeHidden) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SnowflakeCacheView<Role> getRoleCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SnowflakeCacheView<Emote> getEmoteCache() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<List<ListedEmote>> retrieveEmotes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<ListedEmote> retrieveEmoteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<List<Ban>> getBanList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<Ban> getBanById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<Integer> getPrunableMemberCount(int days) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Role getPublicRole() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TextChannel getDefaultChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GuildManager getManager() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GuildController getController() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MentionPaginationAction getRecentMentions() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AuditLogPaginationAction getAuditLogs() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<Void> leave() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<Void> delete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<Void> delete(String mfaCode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AudioManager getAudioManager() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JDA getJDA() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<List<Invite>> getInvites() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestAction<List<Webhook>> getWebhooks() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<GuildVoiceState> getVoiceStates() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public VerificationLevel getVerificationLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public NotificationLevel getDefaultNotificationLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MFALevel getRequiredMFALevel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ExplicitContentLevel getExplicitContentLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean checkVerification() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getCommandSymbol() {
		return commandSymbol;
	}
	public void setCommandSymbol(String commandSymbol) {
		this.commandSymbol = commandSymbol;
	}
	public Guild getGuild() {
		return guild;
	}
	public void setGuild(Guild guild) {
		this.guild = guild;
	}
	public File getSaved() {
		return saved;
	}
	public void setSaved(File saved) {
		this.saved = saved;
	}
	public String getGuildID() {
		return guildID;
	}
	public void setGuildID(String guildID) {
		this.guildID = guildID;
	}
	
}
