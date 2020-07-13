package com.wallet.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserWalletDTO {
	private Long id;
	@NotNull(message = "Informe a id do usuário")
	private Long users;
	@NotNull(message = "Informe a id da carteira")
	private Long wallet;
}
