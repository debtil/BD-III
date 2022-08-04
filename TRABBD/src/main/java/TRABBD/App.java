// ALuno: Matheus Debtil Souza

package TRABBD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main( String[] args ){
        @SuppressWarnings("deprecation")
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Endereco end1 = new Endereco("Julio de Castilho", 2700, "Vila Carli", "Guarapuava");
        session.save(end1);

        Telefone tel1 = new Telefone( 34, 984231269, "Celular");
        Telefone tel2 = new Telefone( 42, 984209761, "Fixo");
        Cliente cliente1 = new Cliente("Matheus Debtil Souza", "06/05/2002", "Masculino", "04499988877", end1);
        tel1.setCliente(cliente1);
        tel2.setCliente(cliente1);

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(tel1);
        telefones.add(tel2);

        cliente1.setTelefones(telefones);

        session.save(cliente1);
        session.save(tel1);
        session.save(tel2);

        Produto prod1 = new Produto("acb123", "Batata doce", 50.00);
        Produto prod2 = new Produto("dce456", "Batata", 55.00);
        Venda venda = new Venda("fge789", cliente1);

        prod1.setVenda(venda);
        prod2.setVenda(venda);

        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(prod1);
        produtos.add(prod2);

        venda.setProdutos(produtos);

        session.save(venda);
        session.save(prod1);
        session.save(prod2);

        tx.commit();
        session.close();
        sf.close();
    }
}
