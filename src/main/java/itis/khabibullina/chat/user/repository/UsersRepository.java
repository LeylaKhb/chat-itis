package itis.khabibullina.chat.user.repository;

import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository  extends JpaRepository<User, UUID> {
}
