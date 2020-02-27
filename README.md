# Penguin GO Backend

### ��Ŀ����

- Penguin GO �ǻ��� Spring Boot��Spring Cloud��Vue��Element ʵ�ֵ� Java ���ٿ���ƽ̨��
- Ŀ���Ǵ��һ�׼�����õĿ��ٽ�����������԰�����������Ч������Ŀ�����ѶȺͳɱ���

### ���ܼƻ�
- ��¼ע�᣺��¼ע�ᣬ��������¼��QQ��΢�š�΢����
- �û������½��û����޸��û���ɾ���û�����ѯ�û�
- ���������½��������޸Ļ�����ɾ����������ѯ����
- ��ɫ�����½���ɫ���޸Ľ�ɫ��ɾ����ɫ����ѯ��ɫ
- �˵������½��˵����޸Ĳ˵���ɾ���˵�����ѯ�˵�
- �ֵ�����½��ֵ䣬�޸��ֵ䣬ɾ���ֵ䣬��ѯ�ֵ�
- ��־������¼�û�������־���鿴ϵͳִ����־
- ����Ȩ�ޣ�����û�������ϵ��ʵ������Ȩ�޹���
- ���ݼ�أ����� Druid ��Ϣ���ṩ��Ч�� SQL ���
- �����¼������ OAuth2, �ṩͳһ�ĵ����¼����
- �ۺ��ĵ������������ĵ����ṩ������۵�API�ĵ�
- ΢���񻯣���� Spring Cloud��ʵ��ϵͳ΢����
- �����أ����΢��������ʵ�ַ����ȫ��λ���
- �������������΢��������ʵ����Ч�ķ�������
- ...

### ����ܹ�

#### ��˼ܹ�

##### ��������

- IDE : eclipse 4.x
- JDK : JDK1.8.x
- Maven : Maven 3.5.x
- MySQL: MySQL 5.7.x

##### ����ѡ��

- ���Ŀ�ܣ�Spring Boot 2.x
- ��ȫ��ܣ�Apache Shiro 1.4.x
- ��ͼ��ܣ�Spring MVC 5.x
- �־ò��ܣ�MyBatis 3.x
- ��ʱ����Quartz 2.x
- ���ݿ����ӳأ�Druid 1.x
- ��־����SLF4J��Log4j

##### ��Ŀ�ṹ

- penguin-pom�� ͳһ���� Maven �汾���������
- penguin-common�� ��������ģ�飬��Ҫ���ù�����
- penguin-core�� ���Ĵ���ģ�飬��Ҫ��װ����ҵ��ģ��
- penguin-admin�� ��̨����ģ�飬�����û�����ɫ���˵������
- penguin-boot�� Spring Boot ����ģ�飬����һЩȫ��������Ϣ

#### ǰ�˼ܹ�

##### ��������

- IDE : VS Code 1.27
- NODE: Node 8.9.x
- NPM : NPM 6.4.x

##### ����ѡ��

- ǰ�˿�ܣ�Vue 2.x
- ҳ�������Element 2.x
- ״̬����Vuex 2.x
- ��̨������axios 0.18.x
- ͼ��ʹ�ã�Font Awesome 4.x

##### ��Ŀ�ṹ

- assets�� ͼ�ꡢ���塢���ʻ���Ϣ�Ⱦ�̬��Ϣ
- components�� ����⣬�Գ���������з�װ
- http�� ��̨����ģ�飬ͳһ��̨�ӿ�����API
- i18n�� ���ʻ�ģ�飬ʹ��Vue i18n���й��ʻ�
- mock�� Mockģ�飬ģ��ӿڵ��ò����ض�������
- permission�� Ȩ�޿���ģ�飬����Ȩ����֤�߼�
- router�� ·�ɹ���ģ�飬����ҳ�����·������
- store�� ״̬����ģ�飬�ṩ�����״̬����
- utils�� ����ģ�飬�ṩһЩͨ�õĹ��߷���
- views�� ҳ��ģ�飬��Ҫ���ø���ҳ����ͼ���


### ��װ�̳�

#### ��˰�װ

1. ����Դ��

    git clone

2. ���빤��

    ʹ�� Eclipse���� Maven ��Ŀ���ڴ�֮ǰ��ȷ���Ѱ�װ JDK �� Maven ���ߡ�

3. �������

    �ҵ� penguin-pom ���̵� pom.xml��ִ�� maven clean install �����������

4. �������ݿ�

    �½� penguin ���ݿ⣬���� penguin-pom ���� doc �µ� SQL �ű��������ʼ�����ݿ⡣

5. ����ϵͳ

    �ҵ� penguin-boot �����µ� PenguinApplication.java, ִ�� Java ����������Ŀ��


#### ǰ�˰�װ

1. ����Դ��

    git clone 

2. �������

    ������Ŀ��Ŀ¼��ִ�� npm install, ���ذ�װ��Ŀ��������

3. ����ϵͳ

    ִ�� npm run dev ���������Ŀ��ͨ�� http://localhost:8080 ���ʡ�

4. ��Ŀ���

    ִ�� npm run build ���������������֮������� dist Ŀ¼��

5. Mock ����

    ͨ���޸�src/mock/index.js�е�openMock����������һ��������ر�Mock���ܡ�


#### ������Ŀ��docker

1. ��ȡtomcat��mysql��������

> sudo docker pull tomcat:latest
> sudo docker pull mysql:latest

    �����������֮��ͨ����������鿴���صľ���
> sudo docker images

2. ����mysql������������ӳ��3306�˿�������3306�˿ڣ���������Ϊmysql��ʹ��mysql:latest����

> sudo docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest

    �鿴�������е�����
> sudo docker ps

    ��������
> sudo docker exec -it  [��������������id]  /bin/bash

    ʹ��navicatԶ������mysql������������penguin���ݿ⣬����sql�ļ�

3. ����tomcat������������ӳ��8080�˿�������8080�˿ڣ���������Ϊtomcat��ʹ��tomcat:latest��������������mysql������dbΪ���������ӱ������������ļ���/usr/soft/tomcat/webappsӳ���������ļ���/usr/local/tomcat/webapps��

> sudo docker run --name tomcat --link mysql:db -p 8080:8080 -v /usr/soft/tomcat/webapps:/usr/local/tomcat/webapps -d tomcat:latest 

    �������������ӣ���Ҫ�������ڰ�װ��Ӧ����
> ping db

    �������а�װmysql������ϵͳ���ݻ�ԭ���

    �����ⲿ���� ������IP:�˿ں�

    ����tomcat��ҳ�ɹ���tomcat��ҳ��GUI������Ҫ�޸������ļ����ܷ��ʣ����忴��Ӧҳ�汨������
> /usr/local/tomcat/conf/tomcat-users.xml
> cat [·��] ֱ��չʾ�ļ�����  sudo gedit [�ļ�] ���ı��༭���� 
> sudo docker cp tomcat:/usr/local/tomcat/...  [·��] �����������ݵ�������

    ͬʱ��tomcat��Ĭ�϶˿ڻ��кܴ���ʻ��ͻ������Ҫ�޸������ļ������ͻ
> /usr/local/tomcat/conf/server.xml

    ע�⣬��Ҫ�޸�application.yml�ļ���jdbc�ķ���·������������������������ʣ�������IP�޸ĳ����������ӱ��������û���������Ҫ��Ҫmysql�����д��ڣ�IP����/��Ȩ��
    
    ��tomcat��Manager App GUIҳ���У�������maven clean install���ɵ�war�ļ������õ���FTP���䣬·����
> /usr/local/tomcat/webapps/��Ŀ��
    
    ǰ��ͬʱҲҪ�޸ĺ�̨�ӿںͱ��ݽӿڵ�ַ��������·��
> /usr/local/tomcat/webapps/��Ŀ��

4. ��������

    ��tomcat�����е�binĿ¼�������������ļ���������������ʹtomcat������ǰ̨������ֱ�ӿ������

> ./catalina.sh run

    ע�⣬���Ҫ�����������java�汾��mysql-connector�汾��Ҫ��docker�а汾��Ӧ�������ĳ�ͻ�Ǻܳ����ģ����忴�������
    

### ʹ��˵��

1. xxxx
2. xxxx
3. xxxx

### ���빱��

1. Fork ����Ŀ
2. �½� Feat_xxx ��֧
3. �ύ����
4. �½� Pull Request

### �ο�
1. [��ѩ���Java�̳�](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945359327200)
2. [�������᳾�Ĳ���](https://www.cnblogs.com/xifengxiaoma/)
3. [Vue.js�����̳�](https://cn.vuejs.org/v2/guide/)