package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();

		// get Current User role has to be implemented
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); // TODO: Add checks

		Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	//1-select game

		//2-get current game
		Block223 block223 = Block223Application.getCurrentGame();
		//3- set Nr of Blocks
		Game game = Block223Application.getCurrentGame();


	}

	public static void deleteGame(String name) throws InvalidInputException {
		Game game = Game.getWithName(name);
		if (game != null) {
			game.delete();
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		Block block = game.getBlock(id);

		block.setRed(red);
		block.setGreen(green);
		block.setBlue(blue);
		block.setPoints(points);

	}

	// Modifier method to position block
	public static void positionBlock(int Id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		Game game = Block223Application.getCurrentGame();

		Level currentlevel = game.getLevel(level);

		// findBlock has to be implemented
		Block block = game.getBlock(Id);

		BlockAssignment blockassignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition,
				currentlevel, block, game);

	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
		UserRole role = Block223Application.getCurrentUserRole();
		Game currentGame = Block223Application.getCurrentGame();
		String error = "";
		if (!(role instanceof Admin)) {
			error = "Admin privileges are required to save a game.";
		} else if (currentGame == null) {
			error = "A game must be selected to save it.";
		} else if (!(currentGame.getAdmin() == role)) {
			error = "Only the admin who created the game can save it.";
		}
		if (error.equals("")) {
			try {
				Block223 block223 = Block223Application.getBlock223();
				Block223Persistence.save(block223);
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		} else {
			throw new InvalidInputException(error);
		}

	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		UserRole role = Block223Application.getCurrentUserRole();
		String error = "";
		if (role == null) {
			error = "Cannot register a new user while a user is logged in.";
		} else if (playerPassword.equals(adminPassword)) {
			error = "The passwords have to be different.";
		}
		if (error.equals("")) {
			try {
				Block223 block223 = Block223Application.getBlock223();
				Player player = new Player(playerPassword, block223);
				User user = new User(username, block223, player);
				if (adminPassword != null && !adminPassword.equals("")) {
					Admin admin = new Admin(adminPassword, block223);
					user.addRole(admin);
				}
				Block223Persistence.save(block223);
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		} else {
			throw new InvalidInputException(error);
		}
	}

	public static void login(String username, String password) throws InvalidInputException {
		UserRole currentRole = Block223Application.getCurrentUserRole();
		String error="";
		User user=User.getWithUsername(username);
		if(currentRole!=null) {
			error="Cannot login a user while a user is already logged in.";
		}else if(user==null) {
			error="The username and password do not match.";
		}else {
			List<UserRole> roles=user.getRoles();
			for(UserRole role:roles) {
				if(role.getPassword().equals(password)) {
					Block223Application.setCurrentUserRole(role);
					Block223Application.resetBlock223();
					return;
				}
			}
			error="The username and password do not match.";
		}
		if(!error.equals("")) {
			throw new InvalidInputException("error");
		}
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() {
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole();//TODO: add checks

		List<TOGame> result = new ArrayList<TOGame>();

		List<Game> games = block223.getGames();

		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();

			if (gameAdmin.equals(admin)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
						game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
						game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
						game.getPaddle().getMinPaddleLength());

				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() {
		Game thisgame = Block223Application.getCurrentGame();
		TOGame to = new TOGame(thisgame.getName(), thisgame.getLevels().size(), thisgame.getNrBlocksPerLevel(),
				thisgame.getBall().getMinBallSpeedX(), thisgame.getBall().getMinBallSpeedY(),
				thisgame.getBall().getBallSpeedIncreaseFactor(), thisgame.getPaddle().getMaxPaddleLength(),
				thisgame.getPaddle().getMinPaddleLength());
		return to;

	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
		Game thisgame = Block223Application.getCurrentGame();
		List<TOBlock> result = new ArrayList<TOBlock>();
		List<Block> blocks = thisgame.getBlocks();
		for (int i = 0; i < blocks.size(); i++) {
			TOBlock to = new TOBlock(blocks.get(i).getId(), blocks.get(i).getRed(), blocks.get(i).getGreen(),
					blocks.get(i).getBlue(), blocks.get(i).getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		Game thisgame = Block223Application.getCurrentGame();
		Block thisblock = thisgame.getBlock(id);

		TOBlock to = new TOBlock(thisblock.getId(), thisblock.getRed(), thisblock.getGreen(), thisblock.getBlue(),
				thisblock.getPoints());

		return to;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		Game thisgame = Block223Application.getCurrentGame();
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level thislevel = thisgame.getLevel(level);
		List<BlockAssignment> assignments = thislevel.getBlockAssignments();
		for (int i = 0; i < assignments.size(); i++) {
			TOGridCell to = new TOGridCell(assignments.get(i).getGridHorizontalPosition(),
					assignments.get(i).getGridVerticalPosition(), assignments.get(i).getBlock().getId(),
					assignments.get(i).getBlock().getRed(), assignments.get(i).getBlock().getGreen(),
					assignments.get(i).getBlock().getBlue(), assignments.get(i).getBlock().getPoints());
			result.add(to);
		}
		return result;

	}

	public static TOUserMode getUserMode() {
		UserRole thisrole = Block223Application.getCurrentUserRole();
		TOUserMode to = new TOUserMode(Mode.None);
		if (thisrole == null) {
			to = new TOUserMode(Mode.None);
		}else if (thisrole instanceof Player) {
			to = new TOUserMode(Mode.Play);
		}else if (thisrole instanceof Admin) {
			to = new TOUserMode(Mode.Design);
		}
		return to;
	}

}