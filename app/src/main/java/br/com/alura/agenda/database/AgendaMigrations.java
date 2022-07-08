package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table aluno add column sobrenome text");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        //Criar nova tabela com as informações desejadas
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists 'Aluno_novo'" +
                    "('id', integer primary key autoincrement not null," +
                    "'nome' text," +
                    "'telefone' text, " +
                    "'email' text)");
            //Copiar dados da tabela antiga para a nova
            database.execSQL("insert into Aluno_novo (id, nome, telefone, email) " +
                    "select id, nome, telefone, email from Aluno");

            //Remove tabela antiga
            database.execSQL("drop table Aluno");

            //Renomear a tabela nova com a o nome da tabela antiga
            database.execSQL("alter table Aluno_novo rename to Aluno");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
@Override
public void migrate(@NonNull SupportSQLiteDatabase database) {
    database.execSQL("alter table Aluno add column momentoDeCadastro integer");
}
};
    static final Migration[] TODAS_MIGRATIONS = {
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4};
}
