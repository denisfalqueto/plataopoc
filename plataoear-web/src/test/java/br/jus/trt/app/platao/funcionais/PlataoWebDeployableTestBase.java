package br.jus.trt.app.platao.funcionais;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Assignable;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.PomEquippedMavenImporter;
import org.jboss.shrinkwrap.resolver.api.maven.strategy.AcceptScopesStrategy;
import org.jboss.shrinkwrap.resolver.api.maven.strategy.MavenResolutionStrategy;
import org.jboss.util.file.Files;
import org.openqa.selenium.WebDriver;

import br.jus.trt.lib.common_tests.DeployableTestBase;

/**
 * Classe base (específica deste projeto) com configurações para rodar testes funcionais no
 * Arquillian
 * 
 * @author David Vieira
 *
 */
@Transactional(value=TransactionMode.DISABLED)
@WarpTest
@RunAsClient
public class PlataoWebDeployableTestBase extends DeployableTestBase {
	
	/***
	 * Injetado somente como workaround de bug na inicialização de testes
	 * que já não injetam o Drone.
	 */
	@Drone
	private WebDriver workaroundDrone;

	/**
	 * Método que se integra ao ciclo de vida do Arquillian para realização do
	 * deploy dos arquivos necessários para execução dos testes no servidor de
	 * aplicação.
	 * 
	 * @return Arquivo elaborado para realização do deploy da aplicação + testes
	 *         no servidor de aplicação.
	 */
	@Deployment(testable=true)
	@OverProtocol("Servlet 3.0") 
	public static Archive<?> createDeployment() {
		
		System.out.println("create EAR - inicio");
		EnterpriseArchive ear = createEarArchive();
		System.out.println("create EAR - fim");
		
		System.out.println("create WAR - inicio");
		WebArchive war = createWarArchive();
		System.out.println("create WAR - fim");
		
		System.out.println("create EJB - inicio");
		JavaArchive ejb = createEjbArchive();
		System.out.println("create EJB - fim");

		installDataLoaderExtension(ejb);

		ear.addAsModule(war);
		ear.addAsModule(ejb);
		
		System.out.println("enviando ear");
		return ear;
	}

	/**
	 * Cria ou retorna um cache do war para deploy.
	 * 
	 * Em cache miss, será criado um arquivo de cache para agilizar as execuções posteriores.
	 * 
	 * Se for alterado um código da aplicação o cache é invalidado 
	 * (o hash dos arquivos de aplicação será diferente).
	 * 
	 * @return War do projeto para deploy
	 */
	private static WebArchive createWarArchive() {
		String cacheFile = "target/war" + calcMD5HashForDir(new File("../plataoear-web/src/main"), new File("../plataoear-web/pom.xml")) + ".cache";
		WebArchive war = null;
		
		if (!new File(cacheFile).exists()) {
			System.out.println("cache miss - " + cacheFile);
			war = createWarCache(cacheFile);
		} else {
			System.out.println("cache hit - " + cacheFile);
			war = loadCache("platao.war", cacheFile, WebArchive.class);
		}
		
		// adicionar classes de testes funcionais
		war.addPackages(true, "br.jus.trt.app.platao.funcionais");
		return war;
	}

	/**
	 * Cria o war e armazena em um zip na pasta /target.
	 * 
	 * @param cacheFile caminho do arquivo cache.
	 * @return war construído e armazenado em cache no /target 
	 */
	private static WebArchive createWarCache(String cacheFile) {
		//configurar o resolver
		MavenResolutionStrategy strategy = new AcceptScopesStrategy(ScopeType.COMPILE, ScopeType.TEST);

		PomEquippedMavenImporter importBuildOutput = ShrinkWrap.create(MavenImporter.class, "platao.war")
				.loadPomFromFile("../plataoear-web/pom.xml")
				.importBuildOutput(strategy);
		
		// gerar war
		WebArchive war = importBuildOutput.as(WebArchive.class);
		// salvar cache
		importBuildOutput.as(ZipExporter.class).exportTo(new File(cacheFile));
		return war;
	}

	/**
	 * Cria ou retorna um cache do ear para deploy.
	 * 
	 * Em cache miss, será criado um arquivo de cache para agilizar as execuções posteriores.
	 * 
	 * Se for alterado um código da aplicação o cache é invalidado 
	 * (o hash dos arquivos de aplicação será diferente).
	 * 
	 * @return Ear do projeto para deploy
	 */
	private static EnterpriseArchive createEarArchive() {
		String cacheFile = "target/ear" + calcMD5HashForDir(new File("../plataoear-ejb/src/main"), new File("../plataoear-ejb/pom.xml")) + ".cache";
		EnterpriseArchive ear = null;
		
		if (!new File(cacheFile).exists()) {
			System.out.println("cache miss - " + cacheFile);
			ear = createEarCache(cacheFile);
		} else {
			System.out.println("cache hit - " + cacheFile);
			ear = loadCache("platao.ear", cacheFile, EnterpriseArchive.class);
		}
		
		return ear;
	}

	/**
	 * Cria o ear e armazena em um zip na pasta /target.
	 * 
	 * @param cacheFile caminho do arquivo cache.
	 * @return ear construído e armazenado em cache no /target 
	 */
	private static EnterpriseArchive createEarCache(String cacheFile) {
		EnterpriseArchive ear;
		ear = ShrinkWrap.create(EnterpriseArchive.class,
				"platao.ear").addAsResource("test-arquillian-application.xml",
				"application.xml");
		addDefaultJbossDeploymentStructure(ear);
		
		File[] libs = loadLibsFromPom("../plataoear-ejb/pom.xml", ScopeType.COMPILE);
		ear.addAsLibraries(libs);
		
		ear.as(ZipExporter.class).exportTo(new File(cacheFile));
		
		return ear;
	}

	/**
	 * Cria ou retorna um cache do Jar ejb para deploy.
	 * 
	 * Em cache miss, será criado um arquivo de cache para agilizar as execuções posteriores.
	 * 
	 * Se for alterado um código da aplicação o cache é invalidado 
	 * (o hash dos arquivos de aplicação será diferente).
	 * 
	 * @return Jar ejb do projeto para deploy
	 */
	private static JavaArchive createEjbArchive() {
		String cacheFile = "target/ejb" + calcMD5HashForDir(new File("../plataoear-ejb/src/main"), new File("../plataoear-ejb/pom.xml")) + ".cache";
		JavaArchive ejb = null;
		
		if (!new File(cacheFile).exists()) {
			System.out.println("cache miss - " + cacheFile);
			ejb = createEjbCache(cacheFile);
		} else {
			System.out.println("cache hit - " + cacheFile);
			ejb = loadCache("platao.jar", cacheFile, JavaArchive.class);
		}
		
		return ejb;
	}
	
	/**
	 * Cria o jar ejb e armazena em um zip na pasta /target.
	 * 
	 * @param cacheFile caminho do arquivo cache.
	 * @return jar ejb construído e armazenado em cache no /target 
	 */
	private static JavaArchive createEjbCache(String cacheFile) {
		String pathTempEJBPom = createTempEJBPomWithJarPackaging("../plataoear-ejb/pom.xml");
		
		MavenResolutionStrategy strategy = new AcceptScopesStrategy(ScopeType.COMPILE);
		Maven.configureResolver().workOffline().withClassPathResolution(true).withMavenCentralRepo(false);
		
		JavaArchive ejb = ShrinkWrap.create(MavenImporter.class, "platao.jar")
				.offline().loadPomFromFile(pathTempEJBPom)
				.importBuildOutput(strategy).as(JavaArchive.class);
		
		Files.delete(pathTempEJBPom);
		
		ejb.as(ZipExporter.class).exportTo(new File(cacheFile));
		
		return ejb;
	}

	/**
	 * Calcula o hash do diretório e do arquivo pom. Dessa forma, qualquer modificação em um
	 * dos dois, gerará um hash diferente.
	 */
	private static String calcMD5HashForDir(File dirToHash, File pom) {

		assert (dirToHash.isDirectory());
		Vector<FileInputStream> fileStreams = new Vector<FileInputStream>();

		collectInputStreams(dirToHash, fileStreams, false);
		try {
			fileStreams.add(new FileInputStream(pom));
		} catch (FileNotFoundException e) {
			throw new AssertionError(e.getMessage()
					+ ": file should never not be found!");
		}

		SequenceInputStream seqStream = new SequenceInputStream(
				fileStreams.elements());

		try {
			String md5Hash = DigestUtils.md5Hex(seqStream);
			seqStream.close();
			return md5Hash;
		} catch (IOException e) {
			throw new RuntimeException("Error reading files to hash in "
					+ dirToHash.getAbsolutePath(), e);
		}

	}

	private static void collectInputStreams(File dir,
			List<FileInputStream> foundStreams, boolean includeHiddenFiles) {

		File[] fileList = dir.listFiles();
		Arrays.sort(fileList, // Need in reproducible order
				new Comparator<File>() {
					public int compare(File f1, File f2) {
						return f1.getName().compareTo(f2.getName());
					}
				});

		for (File f : fileList) {
			if (!includeHiddenFiles && f.getName().startsWith(".")) {
				// Skip it
			} else if (f.isDirectory()) {
				collectInputStreams(f, foundStreams, includeHiddenFiles);
			} else {
				try {
					foundStreams.add(new FileInputStream(f));
				} catch (FileNotFoundException e) {
					throw new AssertionError(e.getMessage()
							+ ": file should never not be found!");
				}
			}
		}

	}

	private static <T extends Assignable> T loadCache(String archiveName, String cacheFile, Class<T> clazz) {
		return ShrinkWrap.create(ZipImporter.class, archiveName).importFrom(new File(cacheFile)).as(clazz);
	}
}
