pipeline {
    agent any

    // 定义环境变量（可选）
    environment {
        MAVEN_HOME = tool 'Maven-3.9.8'  // 对应 Jenkins 中配置的 Maven 工具名称
    }

    stages {
        // 拉取代码
        stage('Checkout') {
            steps {
                checkout scm  // 使用配置的 SCM 拉取代码
                sh 'git log -1'  // 打印最新提交信息
            }
        }

        // 构建项目（以 Maven 为例）
        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
            // 保存构建结果
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
//                     junit 'target/surefire-reports/*.xml'  // 收集测试报告
                }
            }
        }

        // 可选：代码质量检查
//         stage('Quality Check') {
//             steps {
//                 sh "${MAVEN_HOME}/bin/mvn sonar:sonar"  // 需要 SonarQube 集成
//             }
//         }

        // 可选：部署到测试环境
        stage('Deploy to Test') {
            when {
                branch 'master'  // 仅 develop 分支触发
            }
            steps {
                // 使用 Publish Over SSH 插件部署
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'jenkins@192.168.208.12',  // 对应 SSH 服务器配置名称
                        transfers: [
                            sshTransfer(
                                sourceFiles: 'target/*.jar',
                                remoteDirectory: '/home/jenkins/apps/',
//                                 execCommand: 'cd /home/jenkins/apps/ && docker-compose restart'
                                echo "jar move success~~~"
                            )
                        ]
                    )
                ])
            }
        }
    }

    // 构建后操作
    post {
        success {
            echo '构建成功！'
            // 发送通知（邮件、Slack 等）
        }
        failure {
            echo '构建失败！'
            // 发送通知
        }
    }
}