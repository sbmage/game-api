package com.sbmage.common;

public interface CommonMessage {
	String[] ERROR_0000 = { "E0000", "The error occurred." };
	String[] ERROR_0001 = { "E0001", "Body can not be null." };
	String[] ERROR_0002 = { "E0002", "하드캐쉬가 부족합니다." };
	String[] ERROR_0003 = { "E0003", "코인이 부족합니다." };
	String[] ERROR_0004 = { "E0004", "골든슈즈가 부족합니다." };
	String[] ERROR_0005 = { "E0005", "선물함 공간이 부족합니다." };
	String[] ERROR_0006 = { "E0006", "카카오 아이디가 누락되었습니다." };
	String[] ERROR_0007 = { "E0007", "플레이어 아이디가 확인되지 않습니다." };
	String[] ERROR_0008 = { "E0008", "이미 보상을 받았습니다." };
	String[] ERROR_0009 = { "E0009", "사용자 아이디가 누락되었습니다." };
	String[] ERROR_0010 = { "E0010", "친구 아이디가 누락되었습니다." };
	String[] ERROR_0011 = { "E0011", "이메일이 누락되었습니다." };
	String[] ERROR_0012 = { "E0012", "닉네임이 누락되었습니다." };
	String[] ERROR_0013 = { "E0013", "닉네임이 중복되어 사용이 불가능합니다." };
	String[] ERROR_0014 = { "E0014", "The error occurred in decrypting parameter." };
	String[] ERROR_0015 = { "E0015", "팀 아이디가 누락되었습니다." };
	String[] ERROR_0016 = { "E0016", "플레이어 아이디가 누락되었습니다." };
	String[] ERROR_0017 = { "E0017", "이메일이 중복되어 사용이 불가능합니다." };
	String[] ERROR_0018 = { "E0018", "프로세스 키가 누락되었습니다." };
	String[] ERROR_0019 = { "E0019", "개인키가 누락되었습니다." };
	String[] ERROR_0020 = { "E0020", "개인키가 매칭되지 않습니다." };
	String[] ERROR_0021 = { "E0021", "이메일 전송이 실패하였습니다." };
	String[] ERROR_0022 = { "E0022", "비밀번호가 매칭되지 않습니다." };
	String[] ERROR_0023 = { "E0023", "비밀번호가 누락되었습니다." };
	String[] ERROR_0024 = { "E0024", "이메일이 존재하지 않습니다." };
	String[] ERROR_0025 = { "E0025", "TOURNAMENT ID can not be null." };
	String[] ERROR_0026 = { "E0026", "TOURNAMENT CATEGORY ID can not be null." };
	String[] ERROR_0027 = { "E0027", "PLATFORM ID can not be null." };
	String[] ERROR_0028 = { "E0028", "SEQ NO can not be null." };
	String[] ERROR_0029 = { "E0029", "Profile image is missing." };
	String[] ERROR_0030 = { "E0030", "Player rate is missing." };
	String[] ERROR_0031 = { "E0031", "대상 사용자 아이디가 누락되었습니다." };
	String[] ERROR_0032 = { "E0032", "CUP TOURNAMENT ID can not be null." };
	String[] ERROR_0033 = { "E0033", "GAME DATE can not be null." };
	String[] ERROR_0034 = { "E0034", "Invalid Parameter" };

	String[] PLAYER_0000 = { "P0000", "The error occurred in player section." };
	String[] PLAYER_0001 = { "P0001", "플레이어 정보가 존재하지 않습니다." };
	String[] PLAYER_0002 = { "P0002", "Log in from another device." };
	String[] PLAYER_0003 = { "P0003", "EMAIL ID already exists." };
	String[] PLAYER_0004 = { "P0004", "The player already quit." };
	String[] PLAYER_0005 = { "P0005", "Social point have not reached at max." };
	String[] PLAYER_0006 = { "P0006", "You can not receive a social gift anymore." };
	String[] PLAYER_0007 = { "P0007", "Crystal level already reached at max." };
	String[] PLAYER_0008 = { "P0008", "Energy level already reached at max." };
	String[] PLAYER_0009 = { "P0009", "Warp level already reached at max." };
	String[] PLAYER_0010 = { "P0010", "There is no user." };
	String[] PLAYER_0011 = { "P0011", "HP is not enough to training." };
	String[] PLAYER_0012 = { "P0012", "Player is already max level." };
	String[] PLAYER_0013 = { "P0013", "Player is already max stat." };
	String[] PLAYER_0014 = { "P0014", "이미 나이스한 게시물 입니다." };
	String[] PLAYER_0015 = { "P0015", "자신의 게시물은 나이스 할 수 없습니다." };
	String[] PLAYER_0016 = { "P0016", "나이스는 하루 최대 10개만 가능합니다." };

	String[] CHARACTER_0000 = { "C0000", "The character grade already reached at max." };
	String[] CHARACTER_0001 = { "C0001", "The character already exists." };
	String[] CHARACTER_0002 = { "C0002", "The selected character is same with the activated character." };

	String[] SHUTTLE_0000 = { "S0000", "The shuttle already exists." };
	String[] SHUTTLE_0001 = { "S0001", "The shuttle grade already reached at max." };
	String[] SCOUT_0001 = { "SC0001", "1일 1회만 가능합니다." };

	String[] SCOUT_0002 = { "SC0002", "이미 한계 랭크에 도달하였습니다." };
	String[] SCOUT_0003 = { "SC0003", "이미 종료 되었습니다." };
	String[] SCOUT_0004 = { "SC0004", "이미 찬스타임이 발동 중 입니다." };
	String[] SCOUT_0005 = { "SC0005", "아직 박스가 준비되지 않았습니다." };

	String[] ITEM_0000 = { "I0000", "You can't buy a item anymore." };
	String[] ITEM_0001 = { "I0001", "아이템이 부족합니다." };
	String[] ITEM_0002 = { "I0002", "이미 코치 아이템이 발동 중 입니다." };
	String[] ITEM_0003 = { "I0003", "메달이 부족합니다." };

	String[] GIFT_0000 = { "GFT0000", "You can't send a heart gift yet." };

	String[] REQUEST_0000 = { "RQ0000", "You can't send a request message yet." };

	String[] GAME_0000 = { "G0000", "Game ID can not be null." };
	String[] GAME_0001 = { "G0001", "The game dosen't exist." };
	String[] GAME_0002 = { "G0002", "The game was already ended." };
	String[] GAME_0003 = { "G0003", "Item ID & count are not matched." };

	String[] GAME_0004 = { "G0004", "The game did not save scores." };
	String[] GAME_0005 = { "G0005", "유효하지 않은 쿠폰번호입니다." };
	String[] GAME_0006 = { "G0006", "이미 사용된 쿠폰번호입니다." };
	String[] GAME_0007 = { "G0007", "사용할수 없는 쿠폰번호입니다." };
	String[] GAME_0008 = { "G0008", "Invalid game-key." };
	String[] GAME_0009 = { "G0009", "한 계정당 하나의 쿠폰만 유효합니다." };
	String[] GAME_0010 = { "G0010", "NO DATA" };

	String[] CUPGAME_0001 = { "C0001", "CUP Game ID can not be null." };

	String[] MARKET_0001 = { "M0001", "[APPLE] Incorrect Purchase Data." };
	String[] MARKET_0002 = { "M0002", "[GOOGLE] Incorrect Purchase Data." };
	String[] MARKET_0003 = { "M0003", "Product ID is not correct." };
	String[] MARKET_0004 = { "M0004", "Receipt data already exists." };

	String MSG_MARKET_001 = " wrong parameter in buying hard cash.";
	String MSG_MARKET_002 = " wrong parameter in buying soft cash.";

	String[] RECEIPT_0001 = { "R0001", "Error during initialization." };
	String[] RECEIPT_0002 = { "R0002", "Not appropriate parameter." };
	String[] RECEIPT_0003 = { "R0003", "DataBase processing error." };
	String[] RECEIPT_0004 = { "R0004", "This product is not registered. (Check the products table)" };
	String[] RECEIPT_0005 = { "R0005", "Encryption parameter length error." };
	String[] RECEIPT_0006 = { "R0006", "Encryption Error." };
	String[] RECEIPT_0007 = { "R0007", "Not appropriate parameter." };
	String[] RECEIPT_0008 = { "R0008", "Verification in progress error." };
	String[] RECEIPT_0009 = { "R0009", "Not cancel." };

	String[] TEAM_0001 = { "T0001", "추천할 팀이 존재하지 않습니다." };
	String[] TEAM_0002 = { "T0002", "이미 해체되어 삭제된 팀입니다." };
	String[] TEAM_0003 = { "T0003", "팀 정보를 검색할 수 없습니다." };
	String[] TEAM_0004 = { "T0004", "팀 가입 신청 중에 다른 팀에 가입할 수 없습니다." };
	String[] TEAM_0005 = { "T0005", "적합한 팀을 발견하지 못했습니다." };

	String[] TEAM_0031 = { "T0031", "해당 팀은 본선 경기에 출전중입니다." };
	String[] TEAM_0032 = { "T0032", "잘못된 사용자 정보입니다." };
	String[] TEAM_0033 = { "T0033", "승인제 팀의 주장은 사임이 불가능 합니다." };
	String[] TEAM_0034 = { "T0034", "일반 멤버는 사임 대상이 아닙니다." };
	String[] TEAM_0035 = { "T0035", "남아있는 부주장이 없습니다." };
	String[] TEAM_0036 = { "T0036", "3경기 이상 후보 상태가 아닙니다." };
	String[] TEAM_0037 = { "T0037", "부장 혹은 부주장은 탈퇴할 수 없습니다." };
	String[] TEAM_0038 = { "T0038", "해당 팀은 마감되었습니다." };
	String[] TEAM_0039 = { "T0039", "팀이 가득차 더이상 추가 인원을 받을 수 없습니다." };
	String[] TEAM_0040 = { "T0040", "부주장 이상 권한이 필요합니다." };
	String[] TEAM_0041 = { "T0041", "주장 이상 권한이 필요합니다." };
	String[] TEAM_0042 = { "T0042", "팀이 존재하지 않습니다." };
	String[] TEAM_0043 = { "T0043", "자동 소집 대상자 입니다." };
	String[] TEAM_0044 = { "T0044", "팀에 소속되었거나, 승인 대기 상태입니다." };
	String[] TEAM_0045 = { "T0045", "팀에 소속되지 않았지만, 승인 대기 상태입니다. 시스템 관리자에게 문의하세요." };
	String[] TEAM_0046 = { "T0046", "팀에 히스토리가 존재합니다. 팀 해체가 불가능합니다." };
	String[] TEAM_0047 = { "T0047", "리그가 시작된 팀은 13명 이상이 유지되어야 합니다." };
	String[] TEAM_0048 = { "T0048", "팀 소속상태에서는 탈퇴할 수 없습니다." };

	String[] TRADE_0001 = { "TR0001", "아이템이 없거나 수량이 부족합니다." };
	String[] TRADE_0002 = { "TR0002", "플레이어를 소유하고 있지 않거나 상태가 변경되었습니다." };
	String[] TRADE_0003 = { "TR0003", "잘못된 트레이드 정보입니다." };
	String[] TRADE_0004 = { "TR0004", "트레이드 진행 상태가 아닙니다" };
	String[] TRADE_0005 = { "TR0005", "대상이 트레이드 기능을 사용하지 않습니다" };
	String[] TRADE_0006 = { "TR0006", "이미 트레이드를 진행중입니다." };
	String[] TRADE_0007 = { "TR0007", "트레이드 목록에 대표선수가 포함되어있습니다" };

}
