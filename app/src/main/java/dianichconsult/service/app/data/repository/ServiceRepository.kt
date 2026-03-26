package dianichconsult.service.app.data.repository

import dianichconsult.service.app.R
import dianichconsult.service.app.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Cybersecurity Consulting",
            description = "Comprehensive security assessments, penetration testing, and vulnerability management to protect your digital assets. Our certified ethical hackers and security architects identify weaknesses across your infrastructure, applications, and processes before threat actors can exploit them.",
            price = 3500.0,
            category = "Security",
            features = listOf(
                "Vulnerability assessment and scanning",
                "Penetration testing (web, API, network)",
                "Security architecture review",
                "Threat modeling and risk analysis",
                "Incident response planning",
                "Security awareness training"
            ),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.service_cyber,
        ),
        ServiceModel(
            id = 2,
            name = "Cloud Architecture Solutions",
            description = "Design and migration of scalable cloud infrastructure on AWS, Azure, and GCP platforms. We architect resilient, cost-effective cloud environments that accelerate innovation while maintaining enterprise-grade security and compliance standards.",
            price = 4500.0,
            category = "Cloud",
            features = listOf(
                "Cloud readiness assessment",
                "Multi-cloud architecture design",
                "Migration strategy and execution",
                "Infrastructure as Code implementation",
                "Cost optimization and FinOps",
                "24/7 managed cloud services"
            ),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.service_cloud,
        ),
        ServiceModel(
            id = 3,
            name = "Business Process Optimization",
            description = "Streamline operations with data-driven process reengineering and automation strategies. We identify bottlenecks, eliminate waste, and implement intelligent workflows that reduce costs and accelerate delivery across your organization.",
            price = 2800.0,
            category = "Optimization",
            features = listOf(
                "Process discovery and mapping",
                "Bottleneck identification",
                "RPA and workflow automation",
                "KPI framework design",
                "Change management support",
                "Continuous improvement roadmap"
            ),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.service_optimization,
        ),
        ServiceModel(
            id = 4,
            name = "Digital Transformation Strategy",
            description = "End-to-end digital roadmaps that align technology investments with business objectives. We assess digital maturity, define transformation priorities, and guide implementation of modern architectures including microservices, APIs, and event-driven systems.",
            price = 5500.0,
            category = "Strategy",
            features = listOf(
                "Digital maturity assessment",
                "Technology roadmap development",
                "Platform selection advisory",
                "API and integration strategy",
                "Innovation lab workshops",
                "ROI measurement framework"
            ),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.service_strategy,
        ),
        ServiceModel(
            id = 5,
            name = "IT Infrastructure Audit",
            description = "Thorough evaluation of your existing IT systems, networks, and compliance posture. Our independent auditors assess controls, governance structures, and operational processes aligned with ISO 27001, COBIT, and ITIL frameworks.",
            price = 3000.0,
            category = "Security",
            features = listOf(
                "Infrastructure health assessment",
                "Network security evaluation",
                "Compliance gap analysis",
                "IT governance review",
                "Risk register development",
                "Executive summary and action plan"
            ),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.service_audit,
        ),
        ServiceModel(
            id = 6,
            name = "Data Analytics & Insights",
            description = "Turn raw data into actionable intelligence with custom dashboards and predictive models. We design data pipelines, build visualization platforms, and implement machine learning solutions that empower data-driven decision making.",
            price = 3800.0,
            category = "Analytics",
            features = listOf(
                "Data strategy and governance",
                "Dashboard and reporting design",
                "Predictive analytics models",
                "Data pipeline architecture",
                "Business intelligence platform setup",
                "Data literacy training"
            ),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(12, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.service_analytics,
        ),
        ServiceModel(
            id = 7,
            name = "Software Development Consulting",
            description = "Architecture review, code quality assessment, and development process optimization. We help engineering teams adopt best practices in CI/CD, testing, observability, and agile delivery to ship better software faster.",
            price = 3200.0,
            category = "Development",
            features = listOf(
                "Architecture review and design",
                "Code quality assessment",
                "CI/CD pipeline optimization",
                "Test strategy consulting",
                "DevOps maturity assessment",
                "Agile coaching and mentoring"
            ),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(14, 0), LocalTime.of(16, 0)),
            imageRes = R.drawable.service_development,
        ),
        ServiceModel(
            id = 8,
            name = "Network Security & Compliance",
            description = "Ensure regulatory compliance (GDPR, ISO 27001) with robust network security frameworks. We design and implement defense-in-depth strategies, zero trust architectures, and continuous monitoring solutions to safeguard your digital perimeter.",
            price = 4000.0,
            category = "Security",
            features = listOf(
                "GDPR compliance programme",
                "ISO 27001 implementation",
                "Zero trust architecture design",
                "Network segmentation strategy",
                "Security monitoring and SIEM",
                "Breach response procedures"
            ),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.service_network,
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
