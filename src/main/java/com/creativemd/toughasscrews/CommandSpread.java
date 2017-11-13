package com.creativemd.toughasscrews;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.CommandSpreadPlayers;
import net.minecraft.command.CommandTime;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class CommandSpread extends CommandBase {

	@Override
	public String getName() {
		return "spread";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/spread <player> <x> <z> <min> <max> spreads all players across the given radius to a specific location";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 5)
		{
			EntityPlayerMP player = getPlayer(server, sender, args[0]);
			CoordinateArg x = parseCoordinate(sender.getPositionVector().xCoord, args[1], true);
			CoordinateArg z = parseCoordinate(sender.getPositionVector().zCoord, args[2], true);
			int min = parseInt(args[3], 1);
			int max = parseInt(args[4], min);
			Random rand = new Random();
			int random = Math.max(1, max-min);
			int directionX = rand.nextBoolean() ? 1 : -1;
			int directionZ = rand.nextBoolean() ? 1 : -1;
			Position position = new Position(x.getResult() + (rand.nextInt(random) + min) * directionX, z.getResult() + (rand.nextInt(random) + min) * directionZ);
			
			player.setGameType(GameType.SURVIVAL);
			player.setPositionAndUpdate(position.x, position.getSpawnY(player.world), position.z);
		}
	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return index == 0;
	}
	
	protected void setAllWorldTimes(MinecraftServer server, int time)
    {
        for (int i = 0; i < server.worlds.length; ++i)
        {
            server.worlds[i].setWorldTime((long)time);
        }
    }
	
	public static class Position
    {
        double x;
        double z;

        Position()
        {
        }

        Position(double xIn, double zIn)
        {
            this.x = xIn;
            this.z = zIn;
        }

        void normalize()
        {
            double d0 = (double)this.getLength();
            this.x /= d0;
            this.z /= d0;
        }

        float getLength()
        {
            return MathHelper.sqrt(this.x * this.x + this.z * this.z);
        }

        public boolean clamp(double p_111093_1_, double p_111093_3_, double p_111093_5_, double p_111093_7_)
        {
            boolean flag = false;

            if (this.x < p_111093_1_)
            {
                this.x = p_111093_1_;
                flag = true;
            }
            else if (this.x > p_111093_5_)
            {
                this.x = p_111093_5_;
                flag = true;
            }

            if (this.z < p_111093_3_)
            {
                this.z = p_111093_3_;
                flag = true;
            }
            else if (this.z > p_111093_7_)
            {
                this.z = p_111093_7_;
                flag = true;
            }

            return flag;
        }

        public int getSpawnY(World worldIn)
        {
            BlockPos blockpos = new BlockPos(this.x, 256.0D, this.z);

            while (blockpos.getY() > 0)
            {
                blockpos = blockpos.down();

                if (worldIn.getBlockState(blockpos).getMaterial() != Material.AIR)
                {
                    return blockpos.getY() + 1;
                }
            }

            return 257;
        }

        public boolean isSafe(World worldIn)
        {
            BlockPos blockpos = new BlockPos(this.x, 256.0D, this.z);

            while (blockpos.getY() > 0)
            {
                blockpos = blockpos.down();
                Material material = worldIn.getBlockState(blockpos).getMaterial();

                if (material != Material.AIR)
                {
                    return !material.isLiquid() && material != Material.FIRE;
                }
            }

            return false;
        }

        public void randomize(Random rand, double p_111097_2_, double p_111097_4_, double p_111097_6_, double p_111097_8_)
        {
            this.x = MathHelper.nextDouble(rand, p_111097_2_, p_111097_6_);
            this.z = MathHelper.nextDouble(rand, p_111097_4_, p_111097_8_);
        }
    }

}
